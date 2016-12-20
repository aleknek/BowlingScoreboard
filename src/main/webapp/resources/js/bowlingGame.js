function newGame() {
    var form = '<form id="bowlingForm" action="'
        + 'http://'
        + document.location.host
        + '/bowlingGame" method="post"><input type="hidden" name="newGame" /></form>';
    $("body").append(form);
    $('#bowlingForm input').val('true');
    $('#bowlingForm').submit();
}
