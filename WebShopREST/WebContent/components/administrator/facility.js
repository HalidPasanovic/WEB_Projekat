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
	    <main class="d-flex flex-nowrap">
	    <div style="width: 70%; margin: auto;">
	    <div style="display: flex; justify-content: center;">
                    <h3>Facility Details</h3><br>
        </div>
	    <table v-if="facilities" class="table table-striped table-hover table-dark">
	    <tbody>
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
	    <td>Address:<td>
	    <td>{{facilities.location.adress.street + " " + facilities.location.adress.number + " " + facilities.location.adress.place}}</td>
	    </tr>
	    <tr>
	    <td>Average Grade:<td>
	    <td>0.0</td>
	    </tr>
	    <tr>
	    <td>Logo:<td>
	    <td><img src="pictures/picture.png" alt="Logo of the Sport Facillity" width="50" height="50"></td>
	    </tr>
	    </tbody>
	    </table>
	    <br><h5>Trainings in this facility:</h5>
	                    <div>
                    <table class="table table-striped table-hover table-dark">
                    		<thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Type Name</th>
                                <th scope="col">Duration</th>
                                <th scope="col">Description</th>
                                <th scope="col">Trainer</th>
                            </tr>
                            </thead>
                            <tbody>
                        <tr v-for="(t, index) in trainings">
						<td>{{t.name}}</td>
						<td>{{t.type.name}}</td>
						<td>{{t.duration}}</td>
						<td>{{t.description}}</td>
						<td>{{t.trainer.name}}</td>
						</tbody>
						</tr>
                    </table>
                </div>
		<br><h5>Comments for this facility:</h5>
	    <table class="table table-striped table-hover table-dark">
	    <thead>
	    <tr>
	    <th>Customer name</th>
	    <th>Content</th>
	    <th>Rating</th>
	    </tr>
	    </thead>
	    <tbody>
	    <tr v-for="(c, index) in comments">
					<td>{{c.customer.name}}</td>
					<td>{{c.content}}</td>
					<td>{{c.rating}}</td>
		</tr>
		</tbody>
		</table>
		</div>
		</main>
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