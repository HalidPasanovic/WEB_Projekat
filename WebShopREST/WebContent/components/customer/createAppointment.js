Vue.component("createAppointment", { 
	data: function () {
	    return {
            id: -1,
            user: {},
            selectedTraining: {},
            trainingHistory: {}
	    }
	},
	    template: ` 
    <div class="d-flex flex-nowrap">
        <div class="d-flex flex-nowrap" style="width: 100%; margin-top: 2%;">
            <div style="width: 500px">
                <div style="margin-top: 50px;"> 
                    <div>
                        <label for="Type" class="form-label">Training Name</label>
                        <input type="text" class="form-control" :value="selectedTraining?.name" id="Type" placeholder="" disabled>
                    </div>
                    <div>
                        <label for="Type" class="form-label">Type</label>
                        <input type="text" class="form-control" :value="selectedTraining?.type?.name" id="Type" placeholder="" disabled>
                    </div>
                    <div>
                        <label for="Type" class="form-label">Facility</label>
                        <input type="text" class="form-control" :value="selectedTraining?.facility?.name" id="Type" placeholder="" disabled>
                    </div>
                    <div>
                        <label for="Type" class="form-label">Duration</label>
                        <input type="text" class="form-control" :value="selectedTraining?.duration" id="Type" placeholder="" disabled>
                    </div>
                    <div>
                        <label for="Type" class="form-label">Trainer</label>
                        <input type="text" class="form-control" :value="selectedTraining?.trainer?.name" id="Type" placeholder="" disabled>
                    </div>
                    <div>
                        <label for="Type" class="form-label">Description</label>
                        <input type="text" class="form-control" :value="selectedTraining?.description" id="Type" placeholder="" disabled>
                    </div>
                    <div>
                        <label for="Type" class="form-label">Aditional cost</label>
                        <input type="text" class="form-control" :value="selectedTraining?.aditionalCost" id="Type" placeholder="" disabled>
                    </div>
                    <div>
                        <label for="Type" class="form-label">Appointemnt date</label>
                        <input type="date" class="form-control" v-model="trainingHistory.applicationDateTime" id="Type" placeholder="">
                    </div>
                    <button class="w-100 btn btn-lg btn-dark" style="margin-top: 50px;" v-on:click = "CreateAppointment">Create</button>
                </div>
            </div>
        </div>
    </div>
    	`,
    mounted () {
        this.id = this.$route.params.id;
		if (this.id != -1) {
			axios
          		.get('rest/login/loginstat')
          		.then(response => 
				{
					this.user = response.data;
                    this.trainingHistory.customer = response.data;
				})
            axios
          		.get('rest/training/' + this.id)
          		.then(response => 
				{
					this.selectedTraining = response.data;
					this.trainingHistory.training = response.data;
                    this.trainingHistory.trainer = response.data.trainer;
				})
		}
    },
    methods: {
    	
        CreateAppointment : function(){
            axios
          		.post('rest/trainingHistory/' + this.user.id, this.trainingHistory)
          		.then(response => 
				{
					 axios
                        .post('rest/customers/facility/' + this.selectedTraining.facility.id, this.user)
				})
            router.push(`/trainings/`)
        },

        GetValidDate : function(){
            var today = new Date()
            var selected = this.GetSelection()
            var type = selected.type
            if(type === "Yearly"){
                today.setFullYear(today.getFullYear() + 1)
            }
            else if(type === "Monthly"){
                today.setMonth(today.getMonth() + 1)
            }
            else{
                today.setDate(today.getDate() + 7)
            }
            return today.toISOString().slice(0, 10)
        },

        GetSelection : function(){
            return this.membershipTypes.find(item => item.id === this.selectedMembershipType)
        },

        CheckPromo: function(){
            this.promoCodePercentage = 0.3
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
        },

        GetPrice(){
            var startingPrice = this.GetSelection().price
            var userTypeDiscount = this.user.type.discount
            return startingPrice * (1 - userTypeDiscount) * (1 - this.promoCodePercentage)
        }
  }
});