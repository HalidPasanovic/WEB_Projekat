Vue.component("products", { 
	data: function () {
	    return {
	      facilities: null,
	      searchQuery: null
	    }
	},
	    template: ` 
		<div>
			<!-- Header -->
			  <header class="w3-container" style="padding-top:22px">
			    <h5><b><i class="fa fa-dashboard"></i> My Dashboard</b></h5>
			  </header>
			<div style="float: right; margin-right: 1.5%;">
				<div class="search-container">
					<input type="text" class="form-control" v-model="searchQuery" placeholder="Search.." name="search">
				</div>
			</div>
			
			<div style="margin-left: 1%;">
				<div class="search-container">
					<button type="submit" v-on:click = "addProduct">Dodaj</button>
				</div>
			</div>
			<h5>Feeds</h5>
			<table class="w3-table w3-striped w3-white">
				<tr>
					<th>
						Naziv
					</th>
					<th>
						Tip
					</th>
					<th>
						Lokacija
					</th>
					<th>
						Prosecna ocena
					</th>
					<th>
						Radno vreme
					</th>
				</tr>
				<tr v-for="(f, index) in resultQuery">
					<td>{{f.name}}</td>
					<td>{{f.type.name}}</td>
					<td>{{f.location.adress.street + " " + f.location.adress.number + " " + f.location.adress.place}}</td>
					<td>0</td>
					<td>{{f.workRange}}</td>
				</tr>
			</table>
		</div>
    	`,
    mounted () {
        axios
          .get('rest/facility/')
          .then(response => (this.facilities = response.data))
    },
    methods: {
    	addProduct : function() {
    		router.push(`/createFacility/`);
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