
/**
 * Javascript Calculator prototype
 * @param {type} calcCtn container for calculator view
 * @returns {Calc} calculator
 */

var HOST = "127.0.0.1:8080";

// Allowed numeric keys: 0;1;2;3;4;5;6;7;8;9;
var allowedNumericKeyCodes = [48, 49, 50, 51, 52, 53, 54, 55, 56, 57];
// Allowe numpad numeric keys: Numpad 0;Numpad 1;Numpad 2;Numpad 3;Numpad 4;Numpad 5;Numpad 6;Numpad 7;Numpad 8;Numpad 9 
var allowedNumericKeyCodesInNumericPad = [96, 97, 98, 99, 100, 101, 102, 103, 104, 105];
//Allowed delimeter keys: .;,
var allowedDelimeterKeyCodes = [110, 188, 190];
//Allowed operator keys: +;-;*;
var allowedOperatorKeyCodes = [107, 109, 106, 111];
// Allowed other action keys: ESC, DEL, ENTER
var allowedOtherActionKeyCodes = [27, 46, 13];
var allAllowedKeyCodes = [];
$.merge(allAllowedKeyCodes, allowedNumericKeyCodes);
$.merge(allAllowedKeyCodes, allowedNumericKeyCodesInNumericPad);
$.merge(allAllowedKeyCodes, allowedDelimeterKeyCodes);
$.merge(allAllowedKeyCodes, allowedOperatorKeyCodes);
$.merge(allAllowedKeyCodes, allowedOtherActionKeyCodes);

function Calc(calcCtn) {
    this.calcCtn = $(calcCtn);
    this.uuid;
    this.history;
    this.number;
    this.errorDialog;

    this.init = function () {
        console.log("Init calc to element: " + calcCtn);
        this.initInfoView();
        this.initHistoryView();
        this.initActualNumberView();
        this.initNumericButtonsView();
        this.initOperatorAndActionButtonsView();
        this.initModalErrorDialog();
        this.initNumericButtonHandler();
        this.initOperatorAndActionButtonHandler();
        this.initKeyEvent();
        this.initCommunicationSession();
    };

    /**
     * Initializes elements with application informations.
     */
    this.initInfoView = function () {
        console.log("Init info view");
        var infoHeader = $("<h1>SERVER SIDE CALCULATOR</h1>");
        var infoContent = $("<p>Source code: <a href='https://github.com/HonzaSipek/server-side-calc'>https://github.com/HonzaSipek/server-side-calc</a></p>");
        this.calcCtn.append(infoHeader);
        this.calcCtn.append(infoContent);
    };

    /**
     * Initializes element with calculation history.
     */
    this.initHistoryView = function () {
        console.log("Init calculation history view");
        this.history = $('<textarea id="calc-history-area" class="form-control" rows="5" readonly >');
        this.calcCtn.append(this.history);
    };

    /**
     * Initializes element with number.
     */
    this.initActualNumberView = function () {
        console.log("Init actual number view");
        this.number = $('<input id="actual-number-input" class="form-control" type="number" readonly value="0"/>');
        this.calcCtn.append(this.number);
    };

    /**
     * Initializes elements with numeric buttons.
     */
    this.initNumericButtonsView = function () {
        console.log("Init numeric buttons view");
        var numberButtonsCtn = $('<div id="numeric-buttons-ctn"></div>');
        var numberSeven = $('<button class="btn btn-default" value="55">7</button>');
        var numberEight = $('<button class="btn btn-default" value="56">8</button>');
        var numberNine = $('<button class="btn btn-default" value="57">9</button>');
        var numberFour = $('<button class="btn btn-default" value="52">4</button>');
        var numberFive = $('<button class="btn btn-default" value="53">5</button>');
        var numberSix = $('<button class="btn btn-default" value="54">6</button>');
        var numberOne = $('<button class="btn btn-default" value="49">1</button>');
        var numberTwo = $('<button class="btn btn-default" value="50">2</button>');
        var numberThree = $('<button class="btn btn-default" value="51">3</button>');
        var numberZero = $('<button class="btn btn-default" value="48">0</button>');
        var comma = $('<button class="btn btn-default" value="110">.</button>');
        numberButtonsCtn.append(numberSeven);
        numberButtonsCtn.append(numberEight);
        numberButtonsCtn.append(numberNine);
        numberButtonsCtn.append(numberFour);
        numberButtonsCtn.append(numberFive);
        numberButtonsCtn.append(numberSix);
        numberButtonsCtn.append(numberOne);
        numberButtonsCtn.append(numberTwo);
        numberButtonsCtn.append(numberThree);
        numberButtonsCtn.append(numberZero);
        numberButtonsCtn.append(comma);
        this.calcCtn.append(numberButtonsCtn);
        
    };

    /**
     * Initializes elements with operator and action buttons.
     */
    this.initOperatorAndActionButtonsView = function () {
        console.log("Init operator and action buttons view");
        var actionButtonsCtn = $('<div id="action-buttons-ctn"></div>');
        var actionCe = $('<button class="btn btn-danger button-left" value="27">CE</button>');
        var actionC = $('<button class="btn btn-warning button-right" value="46">C</button>');
        var actionDivide = $('<button class="btn btn-default button-left" value="111">/</button>');
        var actionMultiply = $('<button class="btn btn-default button-right" value="106">*</button>');
        var actionSubstract = $('<button class="btn btn-default button-left" value="109">-</button>');
        var actionAdd = $('<button class="btn btn-default button-right" value="107">+</button>');
        var actionEqual = $('<button class="btn btn-default button-equal" value="13">=</button>');
        actionButtonsCtn.append(actionCe);
        actionButtonsCtn.append(actionC);
        actionButtonsCtn.append(actionDivide);
        actionButtonsCtn.append(actionMultiply);
        actionButtonsCtn.append(actionSubstract);
        actionButtonsCtn.append(actionAdd);
        actionButtonsCtn.append(actionEqual);
        this.calcCtn.append(actionButtonsCtn);
    };

    this.initModalErrorDialog = function () {
        console.log("Init modal error dialog view");
        this.errorDialog = $("<div id='myModal' class='modal fade' role='dialog'><div class='modal-dialog'><div class='modal-content'><div class='modal-header'><button type='button' class='close' data-dismiss='modal'>&times;</button><h4 class='modal-title'>Error</h4></div><div class='modal-body'><p></p></div><div class='modal-footer'><button type='button' class='btn btn-default' data-dismiss='modal'>Close</button></div></div></div></div>");
        this.calcCtn.append(this.errorDialog);
    };

    /**
     * Initializes mouse handler of numeric buttons.
     */
    this.initNumericButtonHandler = function () {
        console.log("Init numeric buttons handler");
        var scope = this;
        $("#numeric-buttons-ctn button").mouseup(function (event) {
            var number = $(event.currentTarget).val();
            scope.handleAllowedKey(number);
        });
    };

    /**
     * Initializes mouse handler of operator and action buttons.
     */
    this.initOperatorAndActionButtonHandler = function () {
        console.log("Init operator and action buttons handler");
        var scope = this;
        $("#action-buttons-ctn button").mouseup(function (event) {
            var action = $(event.currentTarget).val();
            scope.handleAllowedKey(action);
        });
    };

    /**
     * Initializes key handler of HTML document.
     */
    this.initKeyEvent = function () {
        console.log("Init key event in HTML document");
        var scope = this;
        $("html").keydown(function (event) {
            if ($.inArray(event.keyCode, allAllowedKeyCodes) >= 0) {
                console.log("Using allowed key:" + event.key
                        + " with code: " + event.keyCode);
                if (event.keyCode === 110 || event.keyCode === 188
                        || event.keyCode === 190) {
                    $("button[value=110]").focus();
                } else {
                    $("button[value=" + event.keyCode + "]").focus();
                }
                scope.handleAllowedKey(event.keyCode);
            } else {
                console.log("Using forbidden key: " + event.key
                        + " with code: " + event.keyCode);
            }
        });
    };

    /**
     * Initializes server communication session.
     */
    this.initCommunicationSession = function () {
        console.log("Init server communication session");
        var scope = this;
        $.ajax({
            type: "GET",
            url: "http://" + HOST + "/calculator/math/init",
            contentType: "application/json; charset=utf-8",
            success: function (data, textStatus, jqXHR) {
                console.log("Receiving session UUID: " + data);
                scope.uuid = data;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("Init server session failed: " + errorThrown);
                scope.showError(errorThrown);
            }
        });
    };

    /**
     * Handles allowed keys.
     * @param {type} keyCode key code
     */
    this.handleAllowedKey = function (keyCode) {
        console.log("Sending math request with key code: " + keyCode
                + " and session UUID: " + this.uuid);
        var scope = this;
        var mathRequest = {
            "key_code": parseInt(keyCode),
            "uuid": scope.uuid
        };
        $.ajax({
            type: "POST",
            url: "http://" + HOST + "/calculator/math",
            data: JSON.stringify(mathRequest),
            contentType: "application/json; charset=utf-8",
            success: function (data, textStatus, jqXHR) {
                console.log("Receiving result of math request: "
                        + JSON.stringify(data));
                if (typeof data.number !== "undefined") {
                    scope.updateNumber(data.number);
                }
                if (typeof data.history !== "undefined") {
                    scope.updateCalcHistory(data.history);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("Sending math request failed: " + errorThrown);
                var response = $.parseJSON(jqXHR.responseText);
                if (typeof response.message !== "undefined") {
                    scope.showError(response.message);
                } else {
                    scope.showError(errorThrown);
                }
            }
        });
    };

    /**
     * Shows modal error dialog.
     * @param {type} errorMessage error message
     */
    this.showError = function (errorMessage) {
        console.log("Showing error: " + errorMessage);
        this.errorDialog.find("p").text(errorMessage);
        this.errorDialog.modal("show");
    };

    /**
     * Updates calculation history.
     * @param {type} calcHistory array of calculation
     */
    this.updateCalcHistory = function (calcHistory) {
        console.log("Setting calculation history to view element: "
                + JSON.stringify(calcHistory));
        var scope = this;
        scope.history.empty();
        calcHistory.reverse();
        $.each(calcHistory, function (index, value) {
            scope.history.append(value);
            scope.history.append("\n");
        });
    };

    /**
     * Updates actual number.
     * @param {type} actualNumber actual number
     */
    this.updateNumber = function (actualNumber) {
        console.log("Setting number to view element: " + actualNumber);
        this.number.val(actualNumber);
    };
}