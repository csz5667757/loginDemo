<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>test</title>
</head>
<script>
    function selectUser() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                document.getElementById("testInnerClass").innerHTML = xmlhttp.responseText;
            }
        }
        xmlhttp.open("POST", "user/showUser.do", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("id=1");
    };
    function selectAllUser() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                document.getElementById("testInnerClass").innerHTML = xmlhttp.responseText;
            }
        }
        xmlhttp.open("POST", "user/selectAll", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    }
</script>
<body>
<p id="test">Hello World!</p>
<button type="button" onclick="selectUser()">select one </button>
<button type="button" onclick="selectAllUser()">select all</button>

</body>
</html>
