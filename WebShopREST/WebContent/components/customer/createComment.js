Vue.component("createComment", { 
	data: function () {
	    return {
            id: -1,
            comment: {},
            facility: {},
	    }
	},
	    template: ` 
<div class="d-flex flex-nowrap">
    <div class="d-flex flex-nowrap" style="width: 100%; margin-top: 2%;">
        <div style="width: 500px">
            <div>
                <label for="Type" class="form-label">Facility</label>
                <input type="text" class="form-control" id="Type" :value ="facility?.name" placeholder="" disabled>
            </div>
            <div>
                <label for="Type" class="form-label">Comment</label>
                <input type="text" class="form-control" id="Type" v-model  ="comment.content" placeholder="">
            </div>
            <div>
                <label for="Name" class="form-label">Rating 1-5</label>
                <input type="number" class="form-control" id="Name" placeholder="" v-model ="comment.rating" min="1" max="5">
            </div>
            <button class="w-100 btn btn-lg btn-dark" v-on:click = "CreateComment">Create</button>
        </div>
    </div>
</div>
    	`,
    mounted () {
        this.id = this.$route.params.id;
        axios
          .get('rest/login/loginstat')
          .then(response => 
            {
                this.comment.customer = response.data;
            })
        axios
            .get('rest/facility/' + this.id)
            .then(response =>  {
                this.comment.facility = response.data
                this.facility = response.data
            })  
    },
    methods: {
    	CreateComment : function() {
            if(this.comment?.rating >= 1 && this.comment?.rating <= 5){
                axios
                    .post('rest/comment/', this.comment)
                router.push(`/`)
            }
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