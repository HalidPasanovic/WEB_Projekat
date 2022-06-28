Vue.component("mainLogin", { 
	data: function () {
	    return {
	      facilities: null,
	      searchQuery: null
	    }
	},
	    template: ` 
		<main class="d-flex flex-nowrap">

            <!-- Sidebar -->

            <div class="b-example-vr d-flex flex-column flex-shrink-0 p-3 text-white bg-dark" style="width: 280px;">
                <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <svg class="bi pe-none me-2" width="40" height="32">
                        <use xlink:href="#bootstrap" />
                    </svg>
                    <span class="fs-4">Sidebar</span>
                </a>
                <hr>
                <ul class="nav nav-pills flex-column mb-auto">
                    <li class="nav-item">
                        <a href="#/" class="nav-link active" aria-current="page">
                            <svg class="bi pe-none me-2" width="16" height="16">
                                <use xlink:href="#home" />
                            </svg>
                            Home
                        </a>
                    </li>
                </ul>
                <hr>
                <div>
                    <a href="#/login" class="d-flex align-items-center text-white text-decoration-none" id="dropdownUser1"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="pictures/ProfilePlaceholderSuit.svg" alt="" width="32" height="32"
                            class="rounded-circle me-2">
                        <strong style="margin-left: 5px;">Login</strong>
                    </a>
                </div>
            </div>

            <!-- Content -->

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
                                <th scope="col">Working hours</th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(f, index) in facilities" @dblclick="viewFacility(f.id)">
                            <td></td>
                            <td>{{f.name}}</td>
                            <td>{{f.type.name}}</td>
                            <td>{{f.location.adress.street + " " + f.location.adress.number + " " + f.location.adress.place}}</td>
                            <td>0</td>
                            <td>{{f.workRange}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
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
        viewFacility : function(id) {
            router.push(`/viewFacility/${id}`);
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