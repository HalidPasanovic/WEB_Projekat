Vue.component("comments", { 
	data: function () {
	    return {
	      comments : null,
	      id: null	
	    }
	},
	    template: `
	    <div>
	    <table>
	    <tr>
	    <th>Customer name</th>
	    <th>Facillity name</th>
	    <th>Content</th>
	    <th>Rating</th>
	    <th>Status</th>
	    </tr>
	    <tr v-for="(c, index) in comments" v-on:click="Oznaceno(c.id)">
					<td>{{c.customer.name}}</td>
					<td>{{c.facility.name}}
					<td>{{c.content}}</td>
					<td>{{c.rating}}</td>
					<td>{{c.status}}</td>
		</tr>
		</table>
		<table>
		<tr>
		<td><button v-on:click="Kliknuto('accept')">Accept</button></td>
		<td><button v-on:click="Kliknuto('reject')">Reject</button></td>
		</tr>
		</table>
		</div>
    	`,
    mounted () {
		this.getComments();
    },
    methods: {
		
		Kliknuto : function(type) {
			if(this.id)
			{
				if(type == 'accept')
			{
				axios
			.put('rest/comment/accept/' + this.id)
			.then(alert("Accepted successfully"))
				.catch((e) => { alert("Exception")})
			}
			else
			{
				axios
			.put('rest/comment/reject/' + this.id)
			.then(alert("Rejected successfully"))
				.catch((e) => { alert("Exception")})
			}
				this.getComments();
			}
			else
			{
				alert("You must pick comment from table")
			}
		},
		Oznaceno: function(id) {
			this.id = id
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