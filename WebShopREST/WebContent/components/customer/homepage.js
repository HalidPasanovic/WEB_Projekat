Vue.component("homepage", { 
	data: function () {
	    return {
	      facilities: null,
	      searchQuery: null
	    }
	},
	    template: ` 
		<div class="d-flex flex-nowrap">
            <div style="width: 100%;">
                <div style="display: flex; justify-content: center;">
                    <h1>Welcome</h1>
                </div>
                <div style="display: flex; justify-content: center; margin-top: 40px;">
                    <h3>Sport Facilities</h3>
                </div>
                <div>
                    <table class="table table-striped table-hover table-dark">
                        <thead>
                            <tr>
                                <th scope="col">Logo</th>
                                <th scope="col">Name</th>
                                <th scope="col">Type</th>
                                <th scope="col">Location</th>
                                <th scope="col">Score</th>
                                <th scope="col">Working hours</th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(f, index) in facilities" @dblclick="viewFacility(f.id)">
                            <td><img :src="'pictures/' + f.logoLocation" alt="" width="100" height="100"
                            class="rounded-circle me-2" style="margin-bottom: 25px;"></td>
                            <td>{{f.name}}</td>
                            <td>{{f.type.name}}</td>
                            <td>{{f.location.adress.street + " " + f.location.adress.number + " " + f.location.adress.place}}</td>
                            <td>{{getRating(f?.id)}}</td>
                            <td>{{f.workRange}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    	`,
    mounted () {
        axios
          .get('rest/facility/')
          .then(response => (this.facilities = response.data))
        axios
          .get('rest/membership/')
    },
    methods: {
    	addProduct : function() {
    		router.push(`/createFacility/`);
    	},
        viewFacility : function(id) {
            router.push(`/viewFacility/${id}`);
            
        },

        getRating: function(id) {
			var rating = 0;
			axios
				.get('rest/comment/rating/' + id)
				.then(response => (
					rating = response.data))
            if(rating === 0){
                return "No ratings"
            }
			return rating
		},

    	editProduct : function(id) {
    		router.push(`/products/${id}`);
    	},
    	deleteProduct : function(id, index) {
    		r = confirm("Are you sure?")
    		if (r){
	    		axios
	            .delete('rest/products/' + id)
	            .then(response => (this.facilities.splice(index, 1)))
    		}
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