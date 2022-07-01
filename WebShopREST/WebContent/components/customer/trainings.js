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
                            </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(f, index) in trainings">
                            <td>{{f?.training?.name}}</td>
                            <td>{{f?.training?.facility?.name}}</td>
                            <td>{{f?.applicationDateTime}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div style="margin-top: 20px; margin-bottom: 20px;">
                    <button class="w-100 btn btn-lg btn-dark" v-on:click = "CreateMembership">Create membership</button>
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
                    .get('rest/trainingHistory/specific/' + this.user.id)
                    .then(response => (this.trainings = response.data))
            })
    },
    methods: {
    	
        CreateMembership : function(){
            router.push(`/membership/`)
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