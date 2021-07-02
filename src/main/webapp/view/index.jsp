<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style type="text/css">
	div{text-align: center;}
	table{width: 600px; margin: auto;}
	table, th, td{
		border: 1px solid darkgray;
		text-align: center;
		border-collapse: collapse;
	}
</style>
</head>
<body>
	<div>
		<button id="btn1">xml 파일 가져오기</button>
		<button id="btn2">json 파일 가져오기</button>
	</div>
	<br><br><br>
	<hr>
	<div>
		<table id="table"></table>
	</div>
</body>
<script type="text/javascript">
   $(function() {
		$("#btn2").click(function() {
			$("#table").empty();
			$.ajax({
				url : "/MyController02",
				methode : "post",
				dataType : "json",
				success : function(data) {
					console.log("data : " +data);
					var table = "<thead>";
					table += "<tr><th>도서관명</th><th>지역</th><th>주소</th><th>휴관일</th><th>전화번호</th><th>위도</th><th>경도</th><tr>";
					table += "</thead><tbody>";
					$.each(data, function(k,v) {
						table += "<tr><td>"+ v["LBRRY_NAME"]+"</td><td>"+v["CODE_VALUE"]+"</td><td>"+ v["ADRES"]
						+"</td><td>"+ v["FDRM_CLOSE_DATE"]+"</td><td>"+ v["TEL_NO"]+"</td><td>"+ v["XCNTS"]
						+"</td><td>"+ v["YDNTS"]+"</td></tr>";
					});
					
					table +="</tbody>";
					$("#table").append(table);
					
				},
				error : function() {
					alert("읽기실패");
				}
			});
		});
		
		$("#btn1").click(function(){
			$("#table").empty();
			$.ajax({
		 		url : "/MyController01",
		 		method : "post",
		 		dataType : "xml",
		 		success : function(data) {
		 			console.log("data : " +data);
		 			var table = "<thead>";
					table += "<tr><th>id</th><th>icon</th><th>설명</th><th>온도</th><th>지역</th></tr>";
					table += "</thead><tbody>";
					$(data).find("local").each(function() {
						table +="<tr>";
						table +="<td>"+$(this).attr("stn_id")+"</td>";
						table +="<td>"+$(this).attr("icon")+"</td>";
						table +="<td>"+$(this).attr("desc")+"</td>";
						table +="<td>"+$(this).attr("ta")+"</td>";
						table +="<td>"+$(this).find("local").text()+"</td>";
						table +="</tr>";
					});
					$("#table").append(table);
				},
				error : function() {
					alert("읽기실패");
				}
		 	});
		})
   });
</script>
</html>


























