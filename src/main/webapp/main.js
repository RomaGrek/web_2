
function validateData(form) {

    let x, y, r, m;

    function isValidNum(num) {
        return !isNaN(parseFloat(num)) && isFinite(num);
    }

    function roundPlus(x, n) {
        m = Math.pow(10, n);
        return Math.round(x * m) / m;
    }


    function validateY() {
        y = document.querySelector("input[name=corY]").value.replace(",", ".");
        if (y === "") {
            $("#text-info").after('<br class="error"><span class="error">Поле Y не может быть пустым!</span>');
            return false;
        } else if (y === '-') {
            $("#text-info").after('<br class="error"><span class="error">Координата Y не может состоять только из минуса</span>');
            return false;
        } else if (!isValidNum(y)) {
            $("#text-info").after('<br class="error"><span class="error">В значении Y могут быть использованы только числа</span>');
            return false;
        } else if (y <= -3 || y >= 3) {
            $("#text-info").after('<br class="error"><span class="error">Неверный диапазон Y (Y = [-2...2])</span>');
            return false;
        }
        if (document.querySelector("input[name=corY]").value.length > 5) {
            document.querySelector("input[name=corY]").value = roundPlus(document.querySelector("input[name=corY]").value.replace(",", "."), 5);
        }
        return true;

    }


    $(".error").remove();
    if (validateY()) {
        // $("#text-info").after('<br class="error"><span class="error">Выполнено!</span>');
        return true;
    }
    return false;

}
$(function() {

    document.getElementById('graphik').addEventListener('click', function (e) {
            var r = document.getElementById('corR').value;
            var element = document.getElementById('text-info');
            if (!r) {
                element.innerHTML = '<br>' + "Вы должны выбрать R!!";
            }
            var svg = document.getElementById('graphik');
            var htmlCoordinatesPoint = svg.createSVGPoint(); // делает точку в системе координат
            htmlCoordinatesPoint.x = e.clientX; // присваиваем значение икса точке
            htmlCoordinatesPoint.y = e.clientY; // присваиваем значение игрики точке
            var svgPoint = htmlCoordinatesPoint.matrixTransform(svg.getScreenCTM().inverse()); // экранные координаты в координаты элемента
            var calcX = (svgPoint.x - 151) * r / 110;
            var calcY = -(svgPoint.y - 121) * r / 87;

            if (calcX >= -4 && calcX <= -3.5) calcX = -3;
            else if (calcX >= -3.5 && calcX < -2.5) calcX = -3;
            else if (calcX >= -2.5 && calcX < -1.5) calcX = -2;
            else if (calcX >= -1.5 && calcX < -0.5) calcX = -1;
            else if (calcX >= -0.5 && calcX < 0.5) calcX = 0;
            else if (calcX >= 0.5 && calcX < 1.5) calcX = 1;
            else if (calcX >= 1.5 && calcX < 2.5) calcX = 2;
            else if (calcX >= 2.5 && calcX < 3.5) calcX = 3;
            else if (calcX >= 3.5 && calcX <= 4.0) calcX = 4;
            else calcX = 999;

            document.getElementById('corX').value = calcX;
            document.getElementById('corY').value = calcY.toFixed(2);

            var getX = svgPoint.x;
            var getY = svgPoint.y;

            var point = document.getElementById('point');

            setAttributes(point, {"cx": String(getX), "cy": String(getY), "r": "1"});

            function setAttributes(el, options) {
                Object.keys(options).forEach(function (attr) {
                    el.setAttribute(attr, options[attr]);
                })
            }
        }
    );
});
