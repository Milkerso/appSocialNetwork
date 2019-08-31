
	<div class="col-md-3 ">
		     <div class="list-group ">
		        <a href="#" class="list-group-item list-group-item-action active">Grupy</a>
                <c:forEach var="g" items="${groupList }">
              <a href="/allpeople/${g.groupId }" class="list-group-item list-group-item-action">${g.name } </a>
               </c:forEach>
            </div>
		</div>