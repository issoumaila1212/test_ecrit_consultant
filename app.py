from flask import Flask, request, jsonify

app = Flask(__name__)

# Endpoint /numbers : Filtrer les nombres pairs
@app.route('/numbers', methods=['POST'])
def filter_numbers():
    data = request.json
    numbers = data.get('numbers', [])
    if not isinstance(numbers, list):
        return jsonify({'error': 'Invalid input format, expected a list of numbers'}), 400
    
    # Filtrer uniquement les nombres pairs
    even_numbers = [num for num in numbers if isinstance(num, int) and num % 2 == 0]
    return jsonify({'even_numbers': even_numbers})

# Endpoint /sum : Additionner deux nombres
@app.route('/sum', methods=['POST'])
def sum_numbers():
    data = request.json
    num1 = data.get('num1')
    num2 = data.get('num2')

    if not (isinstance(num1, int) and isinstance(num2, int)):
        return jsonify({'error': 'Invalid input, expected two integers'}), 400

    result = num1 + num2
    return jsonify({'sum': result})

if __name__ == '__main__':
    app.run(debug=True)
