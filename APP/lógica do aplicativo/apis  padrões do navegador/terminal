from flask import Flask, request, jsonify
import subprocess

# Cria a sua aplicação Flask
app = Flask(__name__)

# Define o endpoint para o terminal
@app.route('/terminal', methods=['POST'])
def run_command():
    # Pega o comando enviado pelo seu navegador
    data = request.get_json()
    command = data.get('command')

    # Verifica se o comando foi recebido
    if not command:
        return jsonify({'error': 'Nenhum comando recebido.'}), 400

    try:
        # Executa o comando no servidor
        result = subprocess.run(
            command,
            shell=True,
            check=True,
            text=True,
            capture_output=True,
            timeout=10
        )
        
        # Retorna o resultado do comando para o seu navegador
        return jsonify({
            'output': result.stdout,
            'error': result.stderr
        })

    except subprocess.CalledProcessError as e:
        # Se o comando falhar, retorna o erro
        return jsonify({
            'output': e.stdout,
            'error': e.stderr
        }), 400
    except Exception as e:
        # Lida com outros erros inesperados
        return jsonify({
            'output': '',
            'error': str(e)
        }), 500

# Executa o servidor Flask
if __name__ == '__main__':
    # Em produção, você usaria um servidor mais robusto
    app.run(host='0.0.0.0', port=5000)