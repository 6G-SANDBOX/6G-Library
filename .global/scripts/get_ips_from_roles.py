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


def get_ips(roles):
    result = {}

    for key, elements in roles.items():
        for element in elements:

            command = "onevm show " + str(element) + " -j | jq \".VM.TEMPLATE.CONTEXT.ETH0_IP\""

            try:
                ip = subprocess.check_output(command, shell=True, text=True)
                result[key] = ip.replace('"', '').strip()

            except subprocess.CalledProcessError as e:
                print(f"Error executing command 'onevm show " + str(element) + " -j | jq \".VM.TEMPLATE.CONTEXT.ETH0_IP\"' " + key + ":" + e)
                sys.exit(1)
                # Capturar errores en caso de que el comando no se ejecute correctamente
                
    return result

def main():
    if len(sys.argv) != 2:
        sys.exit(1)

    json_string = sys.argv[1]

    try:
        data = json.loads(json_string)
        roles = format_service_roles(data)
        result = get_ips(roles)
        print(json.dumps(result, indent=2))

    except json.JSONDecodeError as e:
        print(f"Error al decodificar el JSON: {e}")
        sys.exit(1)

if __name__ == "__main__":
    main()