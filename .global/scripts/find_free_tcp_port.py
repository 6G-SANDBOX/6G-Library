import socket
import random

def check_port(port):
    try:
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
            s.settimeout(1) 
            s.connect(('0.0.0.0', port))
        return False  
    except socket.error:
        return True  

def find_free_port(start_port, end_port):
    ports = list(range(start_port, end_port + 1))
    random.shuffle(ports)
    for port in ports:
        if check_port(port):
            return port
    return None

if __name__ == "__main__":
    start_port = 50000
    end_port = 50500
    
    free_port = find_free_port(start_port, end_port)
    if free_port is not None:
        print(free_port)
    else:
        print("No free port")
