function postReply(replyId) {    	        
	var comment = $('#reply_'+replyId).val();  	
	var userName = $('#userName').val();
	
	var userText = {};
	userText["userName"] = userName;
   	userText["userTxtId"] = replyId;
   	userText["comment"] = comment;       	
    $.ajax({
        headers: { 
    	   'Accept': 'application/json',
    	   'Content-Type': 'application/json' 
    	},
        type: "POST",
        url: "comment",
        data: JSON.stringify(userText),            
        dataType: "json",
	    success: function(response){        	    	
	    	if (response.status == "SUCCESS"){    	   
	    		$('#reply_'+replyId).val('');	    		
	    		userText = response.result.userPost;
	    		$("table#postTbl").empty();
	    		$.each(userText, function(idx, elem){	    			
	    			$('table#postTbl').append('<tr><td class="comment">'+elem.userPost +' <br/> <a href="javascript:onclick=showRow('+elem.userTxtId+');" id="replyLnk" name="replyLnk" >Reply </a></td></tr>');
	    			$('table#postTbl').append('<tr class="hideRow" id="row_'+elem.userTxtId+'"><td class="reply"> <textarea rows="2" cols="110" name="reply_'+elem.userTxtId+'" id="reply_'+elem.userTxtId+'"> </textarea> </td> <td class="replyBtn"><input type="submit" value="Reply" id="replyBtn" name="replyBtn" onClick="postReply('+elem.userTxtId+')"> </td></tr>');
	    			var commentList = elem.comments;
	    			if (commentList != null){
    	    			$.each(commentList, function(id, comm){    	  	    	    				
        	    			$('table#postTbl').append('<tr><td class="commentRow">'+comm.comment +'</td></tr>');        	    			
        	    		});  
	    		   }  
	    		});        	        	    		      	       
	    	} else {
	    		$('#outputTxt').html(response.status);    	    		
	    	}    	          	
        },    	
	    error: function(e){      	    	
	        alert('Error: ' + e);    	
	    }    	
	 });    	
}

function populateUserData(userName) {    	        	     
    $.ajax({
        headers: { 
    	   'Accept': 'application/json',
    	   'Content-Type': 'application/json' 
    	},
        type: "GET",
        url: "comment/"+userName,        
	    success: function(response){        	    	
	    	if (response.status == "SUCCESS"){    	   	    		
	    		userText = response.result.userPost;
	    		$("table#postTbl").empty();
	    		$.each(userText, function(idx, elem){	    			
	    			$('table#postTbl').append('<tr><td class="comment">'+elem.userPost +' <br/> <a href="javascript:onclick=showRow('+elem.userTxtId+');" id="replyLnk" name="replyLnk" >Reply </a></td></tr>');
	    			$('table#postTbl').append('<tr class="hideRow" id="row_'+elem.userTxtId+'"><td class="reply"> <textarea rows="2" cols="110" name="reply_'+elem.userTxtId+'" id="reply_'+elem.userTxtId+'"> </textarea> </td> <td class="replyBtn"><input type="submit" value="Reply" id="replyBtn" name="replyBtn" onClick="postReply('+elem.userTxtId+')"> </td></tr>');
	    			var commentList = elem.comments;
	    			if (commentList != null){
    	    			$.each(commentList, function(id, comm){    	  	    	    				
        	    			$('table#postTbl').append('<tr><td class="commentRow">'+comm.comment +'</td></tr>');        	    			
        	    		});  
	    		   }  
	    		});        	        	    		      	       
	    	} else {
	    		$('#outputTxt').html(response.status);    	    		
	    	}    	          	
        },    	
	    error: function(e){      	    	
	        alert('Error: ' + e);    	
	    }    	
	 });    	
}

function showRow(rowId) {	
	$('#row_'+rowId).removeClass("hideRow");
}

$(document).ready(function(){
    $("#doneBtn").click(function(){
    	var userPost = $('#userPost').val();
        var userName = $('#userName').val();
    	var userPosts = [];    	
    	var userText = {};    	       	    	
       	userPosts.push(userPost);
       	userText["userName"] = userName;
       	userText["userPost"] = userPosts;       	
        $.ajax({
            headers: { 
        	   'Accept': 'application/json',
        	   'Content-Type': 'application/json' 
        	},
            type: "POST",
            url: "userPost",
            data: JSON.stringify(userText),            
            dataType: "json",
    	    success: function(response){        	    	
    	    	if (response.status == "SUCCESS"){        	    		
    	    		userText = response.result.userPost;
    	    		$("table#postTbl").empty();
    	    		$.each(userText, function(idx, elem){    	    			
    	    			$('table#postTbl').append('<tr><td class="comment">'+elem.userPost +' <br/> <a href="javascript:onclick=showRow('+elem.userTxtId+');" id="replyLnk" name="replyLnk" >Reply </a></td></tr>');
    	    			$('table#postTbl').append('<tr class="hideRow" id="row_'+elem.userTxtId+'"><td class="reply"> <textarea rows="2" cols="110" name="reply_'+elem.userTxtId+'" id="reply_'+elem.userTxtId+'"> </textarea> </td> <td class="replyBtn"><input type="submit" value="Reply" id="replyBtn" name="replyBtn" onClick="postReply('+elem.userTxtId+')"> </td></tr>');
    	    			var commentList = elem.comments;
    	    			if (commentList != null){
	    	    			$.each(commentList, function(id, comm){    	  	    	    				
	        	    			$('table#postTbl').append('<tr><td class="commentRow">'+comm.comment +'</td></tr>');        	    			
	        	    		});  
    	    		   }  
    	    		});   
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
    
    $("#userName").blur(function() {
    	var userName = $('#userName').val();
    	populateUserData(userName);
    });
});