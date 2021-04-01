<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="listInformation" class="entity.ListInformation" scope="session" />
<%@ page import="java.util.stream.Stream" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="./style.css">
</head>
<body>
<table class="genTable">
    <tr>
        <th class="authorInfo" colspan="2">
            ФИО: Логинов Роман Алексеевич | Группа: P3232 | Вариант: 644
        </th>
    </tr>
    <tr class="gen-str">
        <td class="graphik">
            Область<br>
            <svg width="310" height="300" id="graphik" xmlns="http://www.w3.org/2000/svg">

                <!— X-Axis —>
                <line class="axis" x1="0" y1="120" x2="360" y2="120" stroke="black"></line>
                <polygon points="310,120 295,130 295,110"></polygon>

                <!— Y-Axis —>
                <line class="axis" x1="150" y1="10" x2="150" y2="240" stroke="black"></line>
                <polygon points="150,10 140,25 160,25"></polygon>

                <!— X-Axis coordinates-->
                <line class="coordinate-line" x1="40" y1="115" x2="40" y2="125" stroke="black"></line>
                <text class="coordinate-text coordinate-text_minus-Rx" x="21" y="110" font-size="14">-R</text>

                <line class="coordinate-line" x1="94" y1="115" x2="94" y2="125" stroke="black"></line>
                <text class="coordinate-text coordinate-text_minus-half-Rx" x="80" y="110" font-size="14">-R/2</text>

                <line class="coordinate-line" x1="205" y1="115" x2="205" y2="125" stroke="black"></line>
                <text class="coordinate-text coordinate-text_plus-half-Rx" x="196" y="110" font-size="14">R/2</text>

                <line class="coordinate-line" x1="260" y1="115" x2="260" y2="125" stroke="black"></line>
                <text class="coordinate-text coordinate-text_plus-Rx" x="255" y="110" font-size="14">R</text>

                <!— Y-Axis coordinates-->
                <line class="coordinate-line" x1="145" y1="206" x2="155" y2="206" stroke="black"></line>
                <text class="coordinate-text coordinate-text_minus-Ry" x="123" y="211" font-size="14">-R</text>

                <line class="coordinate-line" x1="145" y1="163" x2="155" y2="163" stroke="black"></line>
                <text class="coordinate-text coordinate-text_minus-half-Ry" x="115" y="167" font-size="14">-R/2</text>

                <line class="coordinate-line" x1="145" y1="76" x2="155" y2="76" stroke="black"></line>
                <text class="coordinate-text coordinate-text_plus-half-Ry" x="120" y="81" font-size="14">R/2</text>

                <line class="coordinate-line" x1="145" y1="33" x2="155" y2="33" stroke="black"></line>
                <text class="coordinate-text coordinate-text_plus-Ry" x="125" y="38" font-size="14">R</text>

                <!— Triangle —>
                <polygon class="svg-figure triangle-figure" points="40,120 150,120 150,163"
                         fill="blue" fill-opacity="0.25" stroke="darkblue" stroke-opacity="0.5"></polygon>

                <!— Rectangle —>
                <polygon class="svg-figure rectangle-figure" points="150,33 260,33 260,120 150,120"
                         fill="yellow" fill-opacity="0.25" stroke="#CCCC00" stroke-opacity="0.5"></polygon>

                <!— Circle —>
                <path class="svg-figure circle-figure" d="M 150 163 A 77 77 0 0 0 205 120 L 150 120 Z"
                      fill="green" fill-opacity="0.25" stroke="darkgreen" stroke-opacity="0.5" id="points"></path>

                <circle class="point" cx="0" cy="0" r="0" id="point" fill="yellow" stroke="navy" stroke-width="10"></circle>

            </svg>
        </td>
        <td class="inputValue">
            <!--            Входные значения-->
            <form id="input-form" action="ControllerServlet" onsubmit="return validateData(this);" method="POST">
                <table class="inputTable">
                    <caption>Входные значения</caption>
                    <tr>
                        <td id="input-x">
                            <label>
                                Координата X:
                                <select name="corX" id="corX">
                                    <option value="-4">-4</option>
                                    <option value="-3">-3</option>
                                    <option value="-2">-2</option>
                                    <option value="-1">-1</option>
                                    <option value="0">0</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                </select>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td id="input-y">
                            <label>
                                Координата Y:
                                <input type="text" placeholder="-2...2" id="corY" name="corY">
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td id="input-r">
                            <label>
                                Координата R:
                                <select name="corR" id="corR">
                                    <option value="1">1</option>
                                    <option value="1.5">1.5</option>
                                    <option value="2">2</option>
                                    <option value="2.5">2.5</option>
                                    <option value="3">3</option>
                                </select>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td id="input-sub">
                            <input class="but" id="input_p" type="submit" value="Submit">
                        </td>
                    </tr>
                    <tr id="info-str">
                        <td id="info"><b id="text-info">INFO:</b><br>${serverInfo}</td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
    <tr>
        <th id="table_result" colspan="2">
            <div id="result-div">
                <table class="lol" id="table_result_ok">
                    <tr>
                        <th class="cor">X</th>
                        <th class="cor">Y</th>
                        <th class="cor">R</th>
                        <th class="time_real">Время проверки</th>
                        <th class="time_complete">Продолжительность</th>
                        <th class="result">Результат</th>
                    </tr>

                    <c:forEach var="inforamtion" items="${listInformation.listInformation}">
                        <tr>
                            <td>${inforamtion.coordinateX}</td>
                            <td>${inforamtion.coordinateY}</td>
                            <td>${inforamtion.valueR}</td>
                            <td>${inforamtion.runTime}</td>
                            <td>${inforamtion.localTime}</td>
                            <td>${inforamtion.result}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </th>
    </tr>
</table>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
        crossorigin="anonymous"></script>
<script src="./main.js"></script>
</body>
</html>
