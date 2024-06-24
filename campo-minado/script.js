// script.js

const grid = document.getElementById('grid');
const size = 10;
const mineCount = 20;
let cells = [];
let mines = [];

function startGame() {
    grid.innerHTML = '';
    cells = [];
    mines = [];
    createGrid();
    placeMines();
    updateNumbers();
}

function createGrid() {
    for (let row = 0; row < size; row++) {
        const rowArray = [];
        for (let col = 0; col < size; col++) {
            const cell = document.createElement('div');
            cell.classList.add('cell');
            cell.setAttribute('data-row', row);
            cell.setAttribute('data-col', col);
            cell.addEventListener('click', revealCell);
            grid.appendChild(cell);
            rowArray.push(cell);
        }
        cells.push(rowArray);
    }
}

function placeMines() {
    let placedMines = 0;
    while (placedMines < mineCount) {
        const row = Math.floor(Math.random() * size);
        const col = Math.floor(Math.random() * size);
        const cell = cells[row][col];
        if (!cell.classList.contains('mine')) {
            cell.classList.add('mine');
            mines.push(cell);
            placedMines++;
        }
    }
}

function updateNumbers() {
    for (let row = 0; row < size; row++) {
        for (let col = 0; col < size; col++) {
            const cell = cells[row][col];
            if (!cell.classList.contains('mine')) {
                const mineCount = countAdjacentMines(row, col);
                if (mineCount > 0) {
                    cell.textContent = mineCount;
                }
            }
        }
    }
}

function countAdjacentMines(row, col) {
    const directions = [
        [-1, -1], [-1, 0], [-1, 1],
        [0, -1],          [0, 1],
        [1, -1], [1, 0], [1, 1]
    ];
    let count = 0;
    for (let [dx, dy] of directions) {
        const newRow = row + dx;
        const newCol = col + dy;
        if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size) {
            const cell = cells[newRow][newCol];
            if (cell.classList.contains('mine')) {
                count++;
            }
        }
    }
    return count;
}

function revealCell(event) {
    const cell = event.target;
    if (cell.classList.contains('revealed')) return;

    cell.classList.add('revealed');
    if (cell.classList.contains('mine')) {
        alert('Game Over!');
        revealAllMines();
    } else {
        const row = parseInt(cell.getAttribute('data-row'));
        const col = parseInt(cell.getAttribute('data-col'));
        if (cell.textContent === '') {
            revealAdjacentCells(row, col);
        }
    }
}

function revealAdjacentCells(row, col) {
    const directions = [
        [-1, -1], [-1, 0], [-1, 1],
        [0, -1],          [0, 1],
        [1, -1], [1, 0], [1, 1]
    ];
   

