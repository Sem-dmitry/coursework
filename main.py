from flask import Flask, request, jsonify
import tensorflow as tf
import numpy as np

# Загрузка модели
model = tf.keras.models.load_model('mnist_model.h5')

app = Flask(__name__)

def predict(matrix):
    # Преобразование входного массива в нужную форму 
    matrix = np.array(matrix, dtype=np.float32)
    # Инвертирование цветов + нормализация
    matrix = (255 - matrix) / 255
    matrix = np.expand_dims(matrix, axis=0)  # Добавление размерности батча
    # Предсказание модели
    result = model.predict(matrix)
    predicted_digit = int(np.argmax(result))  # Конвертация в int
    probabilities = result.tolist()[0]  # Преобразование в стандартный список
    return predicted_digit, probabilities

@app.route('/train', methods=['POST'])
def train_model():
    return jsonify({"answer": "ok"})

@app.route('/predict', methods=['POST'])
def predict_digit():
    # Получение данных из запроса
    data = request.json
    matrix = np.array(data['matrix']).reshape([28, 28])
    predicted_digit, probabilities = predict(matrix)
    return jsonify({'prediction': predicted_digit, 'probabilities': probabilities})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)#, debug=True)
