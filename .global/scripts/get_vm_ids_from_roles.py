import json
import subprocess
import sys


def format_service_roles(original_json):
    new_json = {}

    for item in original_json:
        name = item.get("name")
        nodes = item.get("nodes")

        if name and nodes:
            new_json[name] = nodes

    return new_json


def get_ids(roles, rsa_key):
    result = {}

    for key, elements in roles.items():
        for element in elements:

            command = "echo 'CONTEXT=[SSH_PUBLIC_KEY=\"" + rsa_key + "\"]'| onevm updateconf " + str(element) + " -a"
            
            try:
                subprocess.check_output(command, shell=True, text=True)
                result[key] = "ok"
                
            except subprocess.CalledProcessError as e:
                print(f"Error executing command 'onevm updateconf ... " + e)
                sys.exit(1)
                # Capturar errores en caso de que el comando no se ejecute correctamente
                
def main():
    if len(sys.argv) != 3:
        sys.exit(1)

    json_string = sys.argv[1]
    rsa_key = sys.argv[1]

    try:
        data = json.loads(json_string)
        roles = format_service_roles(data)
        result = get_ids(roles, rsa_key)

    except json.JSONDecodeError as e:
        print(f"Error al decodificar el JSON: {e}")
        sys.exit(1)

if __name__ == "__main__":
    main()