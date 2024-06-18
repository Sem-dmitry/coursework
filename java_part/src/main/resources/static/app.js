document.addEventListener("DOMContentLoaded", () => {
    const grid = document.getElementById('grid');
    let isDrawing = false;

    for (let i = 0; i < 28 * 28; i++) {
        const cell = document.createElement('div');
        cell.classList.add('cell');
        grid.appendChild(cell);
    }

    function paintCell(cell) {
        cell.style.backgroundColor = 'black';
    }

    grid.addEventListener('mousedown', (e) => {
        isDrawing = true;
        if (e.target.classList.contains('cell')) {
            paintCell(e.target);
        }
    });

    grid.addEventListener('mousemove', (e) => {
        if (isDrawing && e.target.classList.contains('cell')) {
            paintCell(e.target);
        }
    });

    grid.addEventListener('mouseup', () => {
        isDrawing = false;
    });

    grid.addEventListener('mouseleave', () => {
        isDrawing = false;
    });
});

let last_predict = null;

function predictDrawing() {
    const cells = document.querySelectorAll('.cell');
    const matrix = Array.from(cells).map(cell => cell.style.backgroundColor === 'black' ? 0 : 255);

    fetch('/predict', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ matrix })
    }).then(response => response.json())
        .then(data => {
            const resultDiv = document.getElementById('result');
            if (data.prediction !== undefined && data.probabilities !== undefined) {
                let resultHTML = 'Prediction: ' + data.prediction + '<br>';
                last_predict = data.prediction;
                resultHTML += 'Probabilities:<br><ul>';
                data.probabilities.forEach((prob, index) => {
                    resultHTML += `<li>Class ${index}: ${prob.toFixed(4)}</li>`;
                });
                resultHTML += '</ul>';
                resultDiv.innerHTML = resultHTML;
            } else {
                resultDiv.innerText = 'Failed to get prediction: ' + data;
            }
        }).catch(error => {
        document.getElementById('result').innerText = 'Error: ' + error;
    });
}

function saveDrawing() {
    const cells = document.querySelectorAll('.cell');
    const matrix = Array.from(cells).map(cell => cell.style.backgroundColor === 'black' ? 0 : 255);

    fetch('/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({"matrix": matrix, "target": last_predict})
    }).then(r => null)
}
