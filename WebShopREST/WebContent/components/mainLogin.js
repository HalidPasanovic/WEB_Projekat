Vue.component("mainLogin", {
    data: function () {
        return {
            facilities: null,
            searchQuery: null,
            currentSort: 'status',
            currentSortDir: 'desc',
            selectedType: '',
            selectedSearch: '',
            facilityTypes: null,
            showOnlyOpened: false
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
                    <span class="fs-4">FitPass</span>
                </a>
                <hr>
                <ul class="nav nav-pills flex-column mb-auto">
                    <li class="nav-item">
                        <a href="#/" class="nav-link active" aria-current="page">
                            <svg class="bi pe-none me-2" width="16" height="16">
                                <use xlink:href="#home" />
                            </svg>
                            Facilities
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
                <div style="float: right;" class="d-flex flex-nowrap">
                    <div class="form-check" style="margin-right: 10px;">
                        <label class="form-check-label" for="same-address">Open only</label>
                        <input type="checkbox" class="form-check-input" id="same-address" v-model="showOnlyOpened">
                    </div>
                    <div style="width: 150px;">
                        <select v-model = "selectedType" class="form-control">
                            <option value = "" disabled selected>Facility type</option>
                            <option value = "All" >All</option>
                            <option v-for="(f, index) in facilityTypes" :value = "f.name">{{f.name}}</option>
                        </select>
                    </div>
                    <div style="width: 150px;">
                        <select v-model = "selectedSearch" class="form-control">
                            <option value = "" disabled selected>Search by</option>
                            <option value = "name" >Name</option>
                            <option value = "location" >Location</option>
                            <option value = "rating">Rating</option>
                        </select>
                    </div>
                    <div class="search-container">
                        <input type="text" class="form-control btn-dark" v-model="searchQuery" placeholder="Search.." name="search">
                    </div>
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
        </main>
    	`,
    mounted() {
        axios
            .get('rest/facility/dto')
            .then(response => {
                this.facilities = response.data
            })
        axios
            .get('rest/facilitytypes/')
            .then(response => {
                this.facilityTypes = response.data
            })
    },
    methods: {
        addProduct: function () {
            router.push(`/createFacility/`);
        },
        viewFacility: function (id) {
            router.push(`/viewFacility/${id}`);

        },

        getRating: function (rating) {
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

        findProp: function (obj, prop, defval) {
            if (typeof defval == 'undefined') defval = null;
            prop = prop.split('.');
            for (var i = 0; i < prop.length; i++) {
                if (typeof obj[prop[i]] == 'undefined')
                    return defval;
                obj = obj[prop[i]];
            }
            return obj;
        },

        filter: function (item, v) {
            if (this.selectedSearch == '') {
                return true
            }
            if (this.selectedSearch == 'location') {
                var street = this.findProp(item, 'location.adress.street')
                var number = this.findProp(item, 'location.adress.number')
                var place = this.findProp(item, 'location.adress.place')
                return street.toLowerCase().includes(v)
                    || number.toString().toLowerCase().includes(v)
                    || place.toLowerCase().includes(v)
            }
            if (this.selectedSearch == 'rating') {
                var rating = this.getRate(item.rating)
                return rating.toLowerCase().includes(v)
            }
            var value = this.findProp(item, this.selectedSearch)
            return value.toLowerCase().includes(v)
        },

        getRate: function (rating) {
            if (rating === 0) {
                return "No ratings"
            }
            return rating
        },

        getLocation: function (element) {
            return element.location.adress.street + " " + element.location.adress.number + " " + element.location.adress.place
        }
    },
    computed: {
        resultQuery() {
            if (this.facilities) {
                this.facilities = this.facilities.sort((a, b) => {
                    let modifier = 1;
                    if (this.currentSortDir === 'desc') modifier = -1;
                    if (this.currentSort == 'location') {
                        if (this.getLocation(a) < this.getLocation(b)) return -1 * modifier;
                        if (this.getLocation(a) > this.getLocation(b)) return 1 * modifier;
                    } else {
                        if (this.findProp(a, this.currentSort) < this.findProp(b, this.currentSort)) return -1 * modifier;
                        if (this.findProp(a, this.currentSort) > this.findProp(b, this.currentSort)) return 1 * modifier;
                    }
                    return 0;
                })
            }
            var result = this.facilities
            if (this.showOnlyOpened) {
                result = result.filter((item) => {
                    return item.status
                })
            }
            if (this.selectedType != 'All' && this.selectedType != '') {
                result = result.filter((item) => {
                    return item.type.name == this.selectedType
                })
            }
            if (this.searchQuery) {
                result = result.filter((item) => {
                    return this.searchQuery.toLowerCase().split(' ').every(v => this.filter(item, v))
                })
            }
            return result
        }
    }
});