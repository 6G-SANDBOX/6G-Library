# NOTES 

This are internatl notes used for the development of the component.
Main fact is to have a set of important guidlines in case of specific failures, refering mainly to the ROUTE Manager.

## **Problem**

The Build fails because the ansible scripts tries to insert duplicate entries in the Route Manager database (`/opt/route-manager-api/routes.db`). 
The problem persists although when all TNs are destroyed && purged. This is probably because some times the destroy scripts does not destroy every entity, especially when the initial build fails.


Specifically (in our integration) the following routes are noticed:

```console
...
sqlite3 /opt/route-manager-api/routes.db "SELECT * FROM 'Saved Routes' WHERE \"to\"='10.10.10.200/32';"
10.10.10.200/32|10.100.100.33||2025-03-27 11:31:37.000000|2025-03-27 12:31:37.000000|1
root@router-manager-ote:~# ^C
root@router-manager-ote:~# sqlite3 /opt/route-manager-api/routes.db "SELECT * FROM 'Saved Routes' WHERE \"to\"='10.10.10.201/32';"
10.10.10.201/32|10.100.100.33||2025-03-27 11:31:37.000000|2025-03-27 12:31:37.000000|1
...
```


The above makes a 500 ERROR during pipeline as visible via the logs:

```console
...
root@router-manager-ote:~# cat /var/log/route_manager.log | tail -n 50
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/orm/session.py", line 1313, in commit
    self._prepare_impl()
  File "<string>", line 2, in _prepare_impl
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/orm/state_changes.py", line 139, in _go
    ret_value = fn(self, *arg, **kw)
                ^^^^^^^^^^^^^^^^^^^^
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/orm/session.py", line 1288, in _prepare_impl
    self.session.flush()
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/orm/session.py", line 4353, in flush
    self._flush(objects)
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/orm/session.py", line 4488, in _flush
    with util.safe_reraise():
         ^^^^^^^^^^^^^^^^^^^
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/util/langhelpers.py", line 146, in __exit__
    raise exc_value.with_traceback(exc_tb)
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/orm/session.py", line 4449, in _flush
    flush_context.execute()
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/orm/unitofwork.py", line 466, in execute
    rec.execute(self)
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/orm/unitofwork.py", line 642, in execute
    util.preloaded.orm_persistence.save_obj(
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/orm/persistence.py", line 93, in save_obj
    _emit_insert_statements(
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/orm/persistence.py", line 1048, in _emit_insert_statements
    result = connection.execute(
             ^^^^^^^^^^^^^^^^^^^
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/engine/base.py", line 1416, in execute
    return meth(
           ^^^^^
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/sql/elements.py", line 515, in _execute_on_connection
    return connection._execute_clauseelement(
           ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/engine/base.py", line 1638, in _execute_clauseelement
    ret = self._execute_context(
          ^^^^^^^^^^^^^^^^^^^^^^
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/engine/base.py", line 1843, in _execute_context
    return self._exec_single_context(
           ^^^^^^^^^^^^^^^^^^^^^^^^^^
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/engine/base.py", line 1983, in _exec_single_context
    self._handle_dbapi_exception(
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/engine/base.py", line 2352, in _handle_dbapi_exception
    raise sqlalchemy_exception.with_traceback(exc_info[2]) from e
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/engine/base.py", line 1964, in _exec_single_context
    self.dialect.do_execute(
  File "/opt/route-manager-api/.venv/lib/python3.12/site-packages/sqlalchemy/engine/default.py", line 942, in do_execute
    cursor.execute(statement, parameters)
sqlalchemy.exc.IntegrityError: (sqlite3.IntegrityError) UNIQUE constraint failed: Saved Routes.to
[SQL: INSERT INTO "Saved Routes" ("to", via, dev, create_at, delete_at, active) VALUES (?, ?, ?, ?, ?, ?)]
[parameters: ('10.10.10.200/32', '10.100.100.33', None, '2025-10-02 08:54:40.000000', '2025-10-02 09:54:40.000000', 1)]
(Background on this error at: https://sqlalche.me/e/20/gkpj)
...
```

## **QUICK FIX**
**Step1**: Delete the created TN and purge it.

**Step2**: Run the following command to delete all entries in DB.

*Delete the old/stale entries before running Ansible:*
```bash
sqlite3 /opt/route-manager-api/routes.db "DELETE FROM 'Saved Routes' WHERE \"to\"='10.10.10.200/32';"
sqlite3 /opt/route-manager-api/routes.db "DELETE FROM 'Saved Routes' WHERE \"to\"='10.10.10.201/32';"
```

**Step3**: Rerun ansible via the normal TNLCM workflow.

**Note:**
To reinsert the existing values (in case is needed) the following commands should be run:
```bash
sqlite3 /opt/route-manager-api/routes.db "INSERT INTO 'Saved Routes' (to, via, dev, create_at, delete_at, active) VALUES ('10.10.10.200/32', '10.100.100.33', NULL, '2025-03-27 11:31:37.000000', '2025-03-27 12:31:37.000000', 1);"
sqlite3 /opt/route-manager-api/routes.db "INSERT INTO 'Saved Routes' (to, via, dev, create_at, delete_at, active) VALUES ('10.10.10.201/32', '10.100.100.33', NULL, '2025-03-27 11:31:37.000000', '2025-03-27 12:31:37.000000', 1);"

```

## **FUTURE PROOF**

The RouterManager (or ansible code) should be altered so that they not break when tryign to instert an existign path. Rather they should either alter the existing, or ignore it.
