$(document).ready(function(){
    $("#doneBtn").click(function(){
    	var input = $('#userPost').val(); 
    	var inputTxt = {};
    	inputTxt["userPost"] = input;
        $.ajax({
            headers: { 
        	   'Accept': 'application/json',
        	   'Content-Type': 'application/json' 
        	},
            type: "POST",
            url: "text",
            data: JSON.stringify(inputTxt),            
            dataType: "json",
    	    success: function(response){        	    	    	    	    	    	  	    	    
    	    	if (response.status == "SUCCESS"){
    	    		$('#outputTxt').html(response.result.userPost);      	    		
        	        $('#userPost').val('');  
    	    	} else {
    	    		$('#outputTxt').html(response.status);    	    		
    	    	}    	          	
            },    	
    	    error: function(e){    	
    	        alert('Error: ' + e);    	
    	    }    	
    	 });    	
    });
});