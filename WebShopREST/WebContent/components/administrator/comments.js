Vue.component("comments", { 
	data: function () {
	    return {
	      comments : null,
	      id: null
	    }
	},
	    template: `
	    <div>
	    <div style="display: flex; justify-content: center;">
                    <h3>All Comments</h3><br>
        </div>
	    <table class="table table-striped table-hover table-dark">
	    <thead>
	    <tr>
	    <th>Customer name</th>
	    <th>Facillity name</th>
	    <th>Content</th>
	    <th>Rating</th>
	    <th>Status</th>
	    <th>Accept</th>
	    <th>Reject</th>
	    <th>Delete</th>
	    </tr>
	    </thead>
	    <tbody>
	    <tr v-for="(c, index) in comments"">
					<td>{{c.customer.name}}</td>
					<td>{{c.facility.name}}</td>
					<td>{{c.content}}</td>
					<td>{{c.rating}}</td>
					<td>{{c.status}}</td>
					<td><button class="w-100 btn btn-lg btn-dark" v-on:click="Kliknuto('accept',c.id)">Accept</button></td>
					<td><button class="w-100 btn btn-lg btn-dark" v-on:click="Kliknuto('reject',c.id)">Reject</button></td>
					<td><button class="w-100 btn btn-lg btn-dark" v-on:click="Delete(c.id)">Delete</button></td>
		</tr>
		</tbody>
		</table>
		</div>
    	`,
    mounted () {
		this.getComments();
    },
    methods: {
		
		Kliknuto : function(type,id) {
			if(type == 'accept')
			{
				axios
			.put('rest/comment/accept/' + id)
			.then(response => {alert("Accepted successfully"); this.getComments();})
				.catch((e) => { alert("Exception")});
			}
			else
			{
				axios
			.put('rest/comment/reject/' + id)
			.then(response => {alert("Rejected successfully"); this.getComments();})
				.catch((e) => { alert("Exception")});		
			}
			
		},
		Oznaceno: function(id) {
			this.id = id
		},
		
		Delete : function(id) {
			axios.delete('rest/comment/physically/'+ id)
				.then(response => {alert("Deleted succesfully!"); this.getComments();})
		},
		
		getComments : function() {
			axios
			.get('rest/comment/')
				.then(response => (this.comments = response.data))
				.catch((e) => { alert("Exception")})
		}
		
	},
    computed: {
    resultQuery(){
      if(this.searchQuery){
      return this.facilities.filter((item)=>{
        return this.searchQuery.toLowerCase().split(' ').every(v => item.name.toLowerCase().includes(v) 
        	|| item.type.name.toLowerCase().includes(v) 
        	|| item.location.adress.street.toLowerCase().includes(v) 
        	|| item.location.adress.number.toString().toLowerCase().includes(v)
        	|| item.location.adress.place.toLowerCase().includes(v))
      })
      }else{
        return this.facilities;
      }
    }
  }
});