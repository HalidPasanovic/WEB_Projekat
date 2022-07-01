Vue.component("facillity", { 
	data: function () {
	    return {
	      facilities: null,
	      searchQuery: null,
	      pomocna: null,
	      trainings: null,
	      comments : null	
	    }
	},
	    template: `
	    <div>
	    <table v-if="facilities">
	    <tr>
	    <td>Name:<td>
	    <td>{{facilities.name}}</td>
	    </tr>
	    <tr>
	    <td>Type:<td>
	    <td>{{facilities.type.name}}</td>
	    </tr>
	    <tr>
	    <td>Status:<td>
	    <td>{{facilities.status}}</td>
	    </tr>
	    <tr>
	    <td>Work Range:<td>
	    <td>{{facilities.workRange}}</td>
	    </tr>
	    <tr>
	    <td>Work Range:<td>
	    <td>{{facilities.location.adress.street + " " + facilities.location.adress.number + " " + facilities.location.adress.place}}</td>
	    </tr>
	    <tr>
	    <td>Average Grade:<td>
	    <td>0.0</td>
	    </tr>
	    </table>
	    <img src="pictures/picture.png" alt="Logo of the Sport Facillity" width="150" height="150">
	    <p>Trainings in this facility:</p>
	    <table>
	    <tr>
	    <th>Name</th>
	    <th>Type Name</th>
	    <th>Duration</th>
	    <th>Description</th>
	    <th>Trainer</th>
	    </tr>
	    <tr v-for="(t, index) in trainings">
					<td>{{t.name}}</td>
					<td>{{t.type.name}}</td>
					<td>{{t.duration}}</td>
					<td>{{t.description}}</td>
					<td>{{t.trainer.name}}</td>
		</tr>
		</table>
		<p>Comments for this facility:</p>
	    <table>
	    <tr>
	    <th>Customer name</th>
	    <th>Content</th>
	    <th>Rating</th>
	    </tr>
	    <tr v-for="(c, index) in comments">
					<td>{{c.customer.name}}</td>
					<td>{{c.content}}</td>
					<td>{{c.rating}}</td>
		</tr>
		</table>
		</div>
    	`,
    mounted () {
		this.pomocna = this.$route.params.id;
		axios
			.get('rest/facility/' + this.pomocna)
				.then(response => (this.facilities = response.data))
				.catch((e) => { alert("Exception")})
				
		axios
			.get('rest/facility/trainings/' + this.pomocna)
				.then(response => (this.trainings = response.data))
				.catch((e) => { alert("Exception")})
		
		axios
			.get('rest/facility/comments/' + this.pomocna)
				.then(response => (this.comments = response.data))
				.catch((e) => { alert("Exception")})
    },
    methods: {
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