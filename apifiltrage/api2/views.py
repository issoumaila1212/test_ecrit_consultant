from rest_framework.decorators import api_view
from rest_framework.response import Response

# Endpoint pour filtrer les nombres pairs
@api_view(['POST'])
def filter_numbers(request):
    numbers = request.data.get('numbers', [])
    if not isinstance(numbers, list):
        return Response({'error': 'Invalid input format, expected a list of numbers'}, status=400)
    
    even_numbers = [num for num in numbers if isinstance(num, int) and num % 2 == 0]
    return Response({'even_numbers': even_numbers})

# Endpoint pour additionner deux nombres
@api_view(['POST'])
def sum_numbers(request):
    num1 = request.data.get('num1')
    num2 = request.data.get('num2')

    if not (isinstance(num1, int) and isinstance(num2, int)):
        return Response({'error': 'Invalid input, expected two integers'}, status=400)

    result = num1 + num2
    return Response({'sum': result})
