Vue.component("facilitym", { 
	data: function () {
	    return {
	      manager : null,
	      facilities : null,
	      trainers : null,
	      searchQuery: null,
	      customers : null,
	      trainings: null,
	      price1: null,
            price2: null,
            date1: null,
            date2: null,
            currentSort: 'status',
            currentSortDir: 'desc',
            selectedType: '',
            selectedTrainingType: '',
            selectedSearch: '',
            facilityTypes: null,
            trainingTypes: null,
            over: false
	    }
	},
	    template: `
	    <main class="d-flex flex-nowrap" style="overflow-y: auto;">
	    <div style="width: 70%; margin: auto;">
	    <div style="display: flex; justify-content: center;">
                    <h3>Facility Details</h3><br>
        </div>
	    <table v-if="over" class="table table-striped table-hover table-dark">
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
	    <br><h5>Trainers in this facility:</h5>
	    <table class="table table-striped table-hover table-dark">
	    <thead>
	    <tr>
	    <th>Name</th>
	    <th>Surname</th>
	    <th>Username</th>
	    </tr>
	    </thead>
	    <tbody>
	    <tr v-for="(t, index) in trainers">
					<td>{{t.name}}</td>
					<td>{{t.surname}}</td>
					<td>{{t.username}}</td>
		</tr>
		</tbody>
		</table>
		<br><h5>Customers in this facility:</h5>
		<table class="table table-striped table-hover table-dark">
		<thead>
		<tr>
	    <th>Name</th>
	    <th>Surname</th>
	    <th>Username</th>
	    </tr>
	    </thead>
	    <tbody>
		<tr v-for="(t, index) in customers">
					<td>{{t.name}}</td>
					<td>{{t.surname}}</td>
					<td>{{t.username}}</td>
		</tr>
		</tbody>
	    </table>
	    <br><h5>Trainings in this facility:</h5>
	    <div style="float: right;" class="d-flex flex-nowrap">
	    			<div style="width: 150px;">
                        <select v-model = "selectedTrainingType" class="form-control" style="margin-right: 10px; width: 150px;">
                            <option value = "" disabled selected>Training type</option>
                            <option value = "All" >All</option>
                            <option v-for="(f, index) in trainingTypes" :value = "f.name">{{f.name}}</option>
                        </select>
                    </div>
                    <input type="text" class="form-control btn-dark" v-model="searchQuery" placeholder="Name" name="search">
                    <input type="date" class="form-control btn-dark" v-model="date1" placeholder="From" name="search">
                    <input type="date" class="form-control btn-dark" v-model="date2" placeholder="To" name="search">
                    <input type="number" class="form-control btn-dark" v-model="price1" placeholder="From" name="search">
					<input type="number" class="form-control btn-dark" v-model="price2" placeholder="To" name="search">
                            
	    </div>
                    <table class="table table-striped table-hover table-dark sortable">
	    				<thead>
	    					<tr>
	    						<th>Name</th>
	    						<th>Type Name</th>
	    						<th>Trainer</th>
	    						<th>Description</th>
	    						<th>Additional Cost</th>
	    					</tr>
	    				</thead>
	    				<tbody>
						<tr v-for="(t, index) in resultQuery" v-on:click="update(t.id)">
							<td>{{t.name}}</td>
							<td>{{t.type.name}}</td>
							<td>{{t.trainer.name}}</td>
							<td>{{t.description}}</td>
							<td>{{t.aditionalCost}}</td>
						</tr>
						</tbody>
	   				 </table>
                </div>
            </div>
        </div>
        </div>
		</main>
    	`,
    mounted () {
		axios.get('rest/login/loginstat')
          .then(response => 
            {
                axios.get('rest/managers/' + response.data.id)
                .then(response => {this.manager = response.data;
                	
                	axios.get('rest/facility/' + response.data.facilities[0].id)
                	.then(response => {this.facilities = response.data
                	
                		axios.get('rest/training/facility/' + this.facilities.id)
                		.then(response => {
							this.trainers = response.data
							axios.get('rest/training/getfacility/' + this.facilities.id)
							.then(response => {this.trainings = response.data; this.over = true;})
							axios.get('rest/customers/getfacility/' + this.facilities.id)
							.then(response => {this.customers = response.data
							
							})
							})
						
                	})	
                })
            })
                axios
                    .get('rest/trainingType/')
                    .then(response => {
                        this.trainingTypes = response.data
                    })
    },
    methods: {
	
		update : function(id){
			router.push('/update/' + id)
		},
		
		DeleteHistory: function (id, index) {
            r = confirm("Are you sure?")
            if (r) {
                axios
                    .delete('rest/trainingHistory/' + id)
                    .then(response => (this.trainings.splice(index, 1)))
            }
        },

        CheckIfCanDelete: function (time) {
            var selectedTime = Date.parse(time.applicationDateTime)
            var today = new Date()
            today.setDate(today.getDate() + 2)
            return selectedTime > today && time.training.type.id == -1220821779
        },
        sort: function (s) {
            //if s == current sort, reverse
            if (s === this.currentSort) {
                this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
            }
            this.currentSort = s;
        },

        findProp: function (obj, prop, defval) {
            if (typeof defval == 'undefined') defval = null;
            prop = prop.split('.');
            for (var i = 0; i < prop.length; i++) {
                if (typeof obj[prop[i]] == 'undefined')
                    return defval;
                obj = obj[prop[i]];
            }
            return obj;
        }
    },
    computed: {
        resultQuery(){
	
		var temp = this.trainings;
		
      if(this.searchQuery){
      	temp =  temp.filter((item)=>{
        return this.searchQuery.toLowerCase().split(' ').every(v => item.name.toLowerCase().includes(v) )})
      }
      if (this.selectedTrainingType != 'All' && this.selectedTrainingType != '') {
                    temp = temp.filter((item) => {
                        return item.type.name == this.selectedTrainingType
                    })
      }
      
      if ((this.price1 === null || this.price1 === '') || (this.price2 === null || this.price2 === '')) {
            }
            else
            {
					temp = temp.filter((item) => {
                        return item.aditionalCost >= this.price1 && item.aditionalCost <= this.price2
                    })
			}
      return temp;
    	}
   }
});