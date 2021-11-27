<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="../rs/style.css">
</head>
<body>

<script>

refresh();

var IMG_URL = "https://image.tmdb.org/t/p/w500";

function refresh(){
	
	const cnt = document.getElementById("search")
		$.ajax({
		url: '/OpenAPI8.do',
		type: 'GET',
		dataType : 'json',
		contentType : 'application/json',
		//data : { "searchname" : $('#search').val()},
		error:function(request,status,error){ 
			alert('code :' + request.status + "\nerror : " + error);
		}, 
		success:function(result){
			$.each(result,function(key,value){
				console.log(value);
				console.log(value.results);
				 $.each(value.results,function(key,value){
			   
						
			        $("#main").append(
							"<div class='movie'>"+
							"<img src='"+IMG_URL+value.poster_path+"' alt='"+value.title+"'>"
							+"<div class='movie-info'>" 
							+"<h3>" + value.title + "</h3>" 
							+"<span class='"+getColor(value.vote_average)+"'>"+value.vote_average+"</span>"
							+"</div>"
							+"<div class='overview'>" +
							"<h3>Overview</h3>"
							+ value.overview +
							"</div>"
						)
			        
					
				
				}) 
			})
}

});
}

function getColor(vote){
	if(vote>=8){
		return 'green'
	}else if(vote >=5){
		return 'orange'
	}else {
		return 'red'
	}
}


//function login(){
//const searchTerm = search.value;
	
//	if(searchTerm){
//		getMovies(searchURL + "&query=" + searchTerm)
//	} else{
//		getMovies(API_URL);
//	}
//}


</script>

    <header>
        <form id="form" action ="/OpenAPI8.do"  method="get">
            <input type="text" placeholder="Search" id="search" class="search" name = "searchname" value="야호">
            <input type="submit" value = "전송">
        </form>
    </header>
    <main id="main">

    </main>

</body>
</html>