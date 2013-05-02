<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>


<script>
var amoutOfActivities = 0; 
$(function() {
$( "#catalog" ).accordion();
$( "#catalog li" ).draggable({
appendTo: "body",
helper: "clone"
});
$( "#plan ol" ).droppable({
activeClass: "ui-state-default",
hoverClass: "ui-state-hover",
accept: ":not(.ui-sortable-helper)",
drop: function( event, ui ) {
$( this ).find( ".placeholder" ).remove();
$( "<li></li>" ).text( ui.draggable.html().replace(/<div .+/,"").replace('<br>',"   ")).appendTo( this );

var hoursOfDay = 
	   ['<option>1</option>',
	    '<option>2</option>',
	    '<option>3</option>',
	    '<option>4</option>',
	    '<option>5</option>',
	    '<option>6</option>',
	    '<option>7</option>',
	    '<option>8</option>',
	    '<option>9</option>',
	    '<option>10</option>',
	    '<option>11</option>',
	    '<option>12</option>',
	    '<option>13</option>',
	    '<option>14</option>',
	    '<option>15</option>',
	    '<option>16</option>',
	    '<option>17</option>',
	    '<option>18</option>',
	    '<option>19</option>',
	    '<option>20</option>',
	    '<option>21</option>',
	    '<option>22</option>',
	    '<option>23</option>',
	    '<option>24</option>'
	   ].join('\n');
	    
var daysOfWeek = 
	   ['<option>1</option>',
	    '<option>2</option>',
	    '<option>3</option>',
	    '<option>4</option>',
	    '<option>5</option>',
	    '<option>6</option>',
	    '<option>7</option>'
	   ].join('\n');
	   
var activityId = ui.draggable.find("div").text();
var formElement = ['<div>Day off week <select id="a" name="activityCarePlanList[0].dayOfWeek">' + daysOfWeek + '</select>',
                   'Hour of the day<select id="b" name="activityCarePlanList[0].hourOfDay">'+ hoursOfDay +'</select>',
                   '<input id="c" style="display: none" name="activityCarePlanList[0].activityId" value=""/></div>'];


$(formElement.join('\n')).appendTo(this);

$('[id="c"]').attr("value",activityId);
//$('[id="c"]').attr('path','carePlan.activityCarePlanList['+amoutOfActivities+'].activityId');
$('[id="c"]').attr('name','activityCarePlanList['+amoutOfActivities+'].activityId');

$('[id="c"]').attr("id","");
//$('[id="b"]').attr('path','carePlan.activityCarePlanList['+amoutOfActivities+'].hourOfDay');
$('[id="b"]').attr('name','activityCarePlanList['+amoutOfActivities+'].hourOfDay');
$('[id="b"]').attr("id","");
//$('[id="a"]').attr('path','carePlan.activityCarePlanList['+amoutOfActivities+'].dayOfWeek');
$('[id="a"]').attr('name','activityCarePlanList['+amoutOfActivities+'].dayOfWeek');
$('[id="a"]').attr("id","");

amoutOfActivities +=1;


}
}).sortable({
items: "li:not(.placeholder)",
sort: function() {
// gets added unintentionally by droppable interacting with sortable
// using connectWithSortable fixes this, but doesn't allow you to customize active/hoverClass options
$( this ).removeClass( "ui-state-default" );
}
});
});
</script>


	<div id="activities">
		<h1>Activities</h1>
		<div id="catalog">
			<c:forEach items="${categoryList}" var="category">
				<h2><a href="#">${category.categoryName}</a></h2>
				<div>
					 <ul>
						<c:forEach items="${category.activityList}" var="activity">
							<li>
								${activity.activityName}<br>${activity.description}
									 <div style="display: none">${activity.id}</div>
							</li>
							
						</c:forEach>
					</ul>	
				</div>
				
			</c:forEach>
		</div>
	</div>
	<hr/>
	<form:form method="post" modelAttribute="carePlan">
		<ul>
			<li><form:label path="carePlanName"/>Care plan name</li>
			<li><form:input path="carePlanName" type="text"/></li>
			<li><form:errors path="carePlanName"></form:errors></li>
		</ul>
		<div id="plan">
			<h1>Care plan</h1>
			
				<div  style="border: solid;">
					<ol>
						<li class="placeholder">Add activities here<br><br><br></li>
					</ol>
				</div>
				<input type="submit" value="Add care plan"/>
			
		</div>
	</form:form>
