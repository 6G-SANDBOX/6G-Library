#!/bin/bash

# default values
K="(UNHEX(NULL))"
AMF="(UNHEX('8000'))"
OP="(UNHEX('00000000000000000000000000000000'))"
SQN="000000000017"
AUTH_TYPE="0"
OP_IS_OPC="0"
USIM_TYPE="0"
DEBUG=0
DOMAIN_NAME="mnc001.mcc001.3gppnetwork.org"

usage() { echo "Usage: $0 [-S <SUPI>] [-k <KEY>] [-a <AMF>] [-o <OP>] [-s <SQN>] [-t <AUTH_TYPE>] [-c <OP_IS_OPC>] [-u <USIM_TYPE>] [-n <DOMAIN_NAME (default: mnc001.mcc001.3gppnetwork.org)>] [-T <PHONE_NUMBER>] [-d <DEBUG>]

	- AUTH_TYPE 0 - 5G_AKA, 1 - EAP_AKA_PRIME
	- IF OPc key is available, set OP_IS_OPC flag
	- USIM_TYPE can be 0 - milenage, 1 - xor
	- DEBUG - use this flag to get command which will be executed(dry run only)

    - e.g.: ./provision_user.sh -S 001010000012345 -k 00000000000000000000000000000000 -a 8000 -o 00000000000000000000000000000000 -s 000000000010 -t 0 -c 1 -u 0 -T 12345
	" 1>&2; exit 1; }

while getopts "hS:k:a:o:s:t:c:u:n:T:d" o; do
	case "${o}" in
	[S])
		SUPI="$OPTARG"
		;;
	[k])
		K="(UNHEX('$OPTARG'))"
		;;
	[a])
		AMF="(UNHEX('$OPTARG'))"
		;;
	[o])
		OP="(UNHEX('$OPTARG'))"
		;;
	[s])
		SQN="$OPTARG"
		;;
	[t])
		AUTH_TYPE="$OPTARG"
		;;
	[c])
		OP_IS_OPC="$OPTARG"
		;;
	[u])
		USIM_TYPE="$OPTARG"
		;;
    [n])
		DOMAIN_NAME="$OPTARG"
		;;
    [T])
		PHONE_NUMBER="$OPTARG"
		;;
	[d])
		DEBUG=1
		;;
	*|h)
		usage
		echo "Parameters with a capital letter are mandatory!"
		exit 1
		;;
	esac
done
shift $((OPTIND-1))

if [ -z "${SUPI}" ]; then
	echo "parameter missing.."
	usage
fi

t=$(tempfile) || exit
trap "rm -f -- '$t'" EXIT

[ -z $PHONE_NUMBER ] && PHONE_NUMBER=$SUPI

printf "USE udm_db;\n" > $t
printf "SET @newimsi := \"$SUPI\";\n" >> $t
printf 'INSERT INTO `supi` VALUES (0,"%s",%s,%s,%s,"%s",%d,%d,%d);\n' ${SUPI} ${K} ${AMF} ${OP} ${SQN} ${AUTH_TYPE} ${OP_IS_OPC} ${USIM_TYPE} >> $t
printf "SELECT @id_supi := last_insert_id();\n" >> $t
#INSERT INTO `impi` VALUES (6,33,'001010000049726@ims.mnc001.mcc001.3gppnetwork.org','sip:scscf.ims.mnc001.mcc001.3gppnetwork.org:6060',0,NULL,1,1);
printf "INSERT INTO impi VALUES (0,@id_supi,concat(@newimsi,'@ims.$DOMAIN_NAME'),concat('sip:scscf','.ims.$DOMAIN_NAME:6060'),0,NULL,1,1);\n" >> $t
printf "SELECT @impi := last_insert_id();\n" >> $t
#### IMPU insert 1
#INSERT INTO `impu` VALUES (13,'sip:001010000049726@ims.mnc001.mcc001.3gppnetwork.org',0,0,1,1,0,NULL);
printf "INSERT INTO impu VALUES (0,concat('sip:',@newimsi,'@ims.$DOMAIN_NAME'),0,0,1,1,0,1);\n" >> $t
printf "SELECT @impu := last_insert_id();\n" >> $t
printf "INSERT INTO impi_impu VALUES (@impi,@impu,1);\n" >> $t
#### IMPU insert 2
#INSERT INTO `impu` VALUES (14,'tel:49726',0,0,1,1,0,NULL);
printf "INSERT INTO impu VALUES (0,concat('tel:',$PHONE_NUMBER),0,0,1,1,0,1);\n" >> $t
printf "SELECT @impu := last_insert_id();\n" >> $t
printf "INSERT INTO impi_impu VALUES (@impi,@impu,1);\n" >> $t
#### IMPU insert 3
#INSERT INTO `impu` VALUES (15,'sip:49726',0,0,1,1,0,NULL);
printf "INSERT INTO impu VALUES (null,concat('sip:',$PHONE_NUMBER),0,0,1,1,0,1);\n" >> $t
printf "SELECT @impu := last_insert_id();\n" >> $t
printf "INSERT INTO impi_impu VALUES (@impi,@impu,1);\n" >> $t

if [[ $DEBUG == 0 ]]; then
	echo "tmp file: $t, added user: ${SUPI}"
	#cat $t
	mysql udm_db < "$t"
else
	echo "sqlfile: $t"
	cat $t
fi

rm -f -- "$t"
trap - EXIT
