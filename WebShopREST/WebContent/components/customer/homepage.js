Vue.component("homepage", {
    data: function () {
        return {
            facilities: null,
            searchQuery: null,
            currentSort: 'name',
            currentSortDir: 'asc'
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
                <div style="float: right; margin-right: 1.5%;">
                    <div class="search-container">
                        <input type="text" class="form-control" v-model="searchQuery" placeholder="Search.." name="search">
                    </div>
			    </div>
                <div>
                    <table class="table table-striped table-hover table-dark">
                        <thead>
                            <tr>
                                <th scope="col">Logo</th>
                                <th @click="sort('name')" scope="col">Name</th>
                                <th @click="sort('type.name')" scope="col">Type</th>
                                <th  scope="col">Location</th>
                                <th scope="col">Score</th>
                                <th @click="sort('workRange')" scope="col">Working hours</th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(f, index) in resultQuery" @dblclick="viewFacility(f.id)">
                            <td><img :src="'pictures/' + f.logoLocation" alt="" width="100" height="100"
                            class="rounded-circle me-2" style="margin-bottom: 25px;"></td>
                            <td>{{f.name}}</td>
                            <td>{{f.type.name}}</td>
                            <td>{{f.location.adress.street + " " + f.location.adress.number + " " + f.location.adress.place}}</td>
                            <td>{{getRate(f?.rating)}}</td>
                            <td>{{f.workRange}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    	`,
    mounted() {
        axios
            .get('rest/facility/dto')
            .then(response => {
                this.facilities = response.data
            })
    },
    methods: {
        addProduct: function () {
            router.push(`/createFacility/`);
        },
        viewFacility: function (id) {
            router.push(`/viewFacility/${id}`);

        },

        getRate: function (rating) {
            if (rating === 0) {
                return "No ratings"
            }
            return rating
        },

        editProduct: function (id) {
            router.push(`/products/${id}`);
        },
        deleteProduct: function (id, index) {
            r = confirm("Are you sure?")
            if (r) {
                axios
                    .delete('rest/products/' + id)
                    .then(response => (this.facilities.splice(index, 1)))
            }
        },

        sort: function (s) {
            //if s == current sort, reverse
            if (s === this.currentSort) {
                this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
            }
            this.currentSort = s;
        },

        findProp: function(obj, prop, defval){
            if (typeof defval == 'undefined') defval = null;
            prop = prop.split('.');
            for (var i = 0; i < prop.length; i++) {
                if(typeof obj[prop[i]] == 'undefined')
                    return defval;
                obj = obj[prop[i]];
            }
            return obj;
        }
    },
    computed: {
        resultQuery() {
            if(this.facilities){
                this.facilities = this.facilities.sort((a, b) => {
                    let modifier = 1;
                    if (this.currentSortDir === 'desc') modifier = -1;
                    if (this.findProp(a, this.currentSort) < this.findProp(b, this.currentSort)) return -1 * modifier;
                    if (this.findProp(a, this.currentSort) > this.findProp(b, this.currentSort)) return 1 * modifier;
                    return 0;
                })
            }
            if (this.searchQuery) {
                return this.facilities.filter((item) => {
                    return this.searchQuery.toLowerCase().split(' ').every(v => item.name.toLowerCase().includes(v)
                        || item.type.name.toLowerCase().includes(v)
                        || item.location.adress.street.toLowerCase().includes(v)
                        || item.location.adress.number.toString().toLowerCase().includes(v)
                        || item.location.adress.place.toLowerCase().includes(v))
                })
            } else {
                return this.facilities;
            }
        },

        getRating() {
            var rating = 0;
            axios
                .get('rest/comment/rating/' + id)
                .then(response => {
                    rating = response.data
                })
            if (rating === 0) {
                return "No ratings"
            }
            return rating
        },
    }
});