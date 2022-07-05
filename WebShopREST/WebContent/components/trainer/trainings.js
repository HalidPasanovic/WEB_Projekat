Vue.component("trainings", { 
	data: function () {
	    return {
            user: null,
	        trainings: null,
	        searchQuery: null
	    }
	},
	    template: ` 
		<div class="d-flex flex-nowrap">
            <div style="width: 100%;">
                <div style="display: flex; justify-content: center; margin-top: 20px; margin-bottom: 20px;">
                    <h3>All trainings</h3>
                </div>
                <div>
                    <table class="table table-striped table-hover table-dark">
                        <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Sport facility</th>
                                <th scope="col">Date</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(f, index) in trainings">
                            <td>{{f?.training?.name}}</td>
                            <td>{{f?.training?.facility?.name}}</td>
                            <td>{{f?.applicationDateTime}}</td>
                            <td><button v-if="CheckIfCanDelete(f?.applicationDateTime)" class="btn btn-dark" v-on:click = "DeleteHistory(f?.id, index)">Delete</button></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div style="margin-top: 20px; margin-bottom: 20px;">
                    <button class="w-100 btn btn-lg btn-dark" v-on:click = "Personal">Group trainings</button>
                    <button class="w-100 btn btn-lg btn-dark" v-on:click = "Group">Personal trainings</button>
                </div>
            </div>
        </div>
    	`,
    mounted () {
        axios
          .get('rest/login/loginstat')
          .then(response => 
            {
                this.user = response.data;
                axios
                    .get('rest/trainingHistory/specific/trainer/' + this.user.id)
                    .then(response => (this.trainings = response.data))
            })
    },
    methods: {

        DeleteHistory: function(id, index){
			r = confirm("Are you sure?")
    		if (r){
	    		axios
	            .delete('rest/trainingHistory/' + id)
	            .then(response => (this.trainings.splice(index, 1)))
    		}
		},

        CheckIfCanDelete : function(time) {
            var selectedTime = Date.parse(time)
            var today = new Date()
            today.setDate(today.getDate() + 2)
			return selectedTime > today
		},

        Personal : function(){
            router.push(`/personalTrainings`)
        },

        Group : function(){
            router.push(`/groupTrainings`)
        },
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