Vue.component("homepage", {
    data: function () {
        return {
            facilities: null,
            nameQuery: null,
            locationQuery: null,
            ratingQuery: null,
            currentSort: 'status',
            currentSortDir: 'desc',
            selectedType: '',
            selectedSearch: '',
            facilityTypes: null,
            showOnlyOpened: false
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
                    <input style="width: 150px;" type="text" class="form-control btn-dark" v-model="nameQuery" placeholder="Name" name="search">
                    <input style="width: 150px;" type="text" class="form-control btn-dark" v-model="locationQuery" placeholder="Location" name="search">
                    <input style="width: 150px;" type="number" class="form-control btn-dark" v-model="ratingQuery" placeholder="Rating" name="search">
			    </div>
                <div>
                    <table class="table table-striped table-hover table-dark">
                        <thead>
                            <tr>
                                <th scope="col">Logo</th>
                                <th @click="sort('name')" scope="col">Name</th>
                                <th @click="sort('type.name')" scope="col">Type</th>
                                <th @click="sort('location')" scope="col">Location</th>
                                <th @click="sort('rating')" scope="col">Score</th>
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

        filterName: function (item, v) {
            var value = this.findProp(item, 'name')
            return value.toLowerCase().includes(v)
        },

        filterLocation: function (item, v) {
            var street = this.findProp(item, 'location.adress.street')
            var number = this.findProp(item, 'location.adress.number')
            var place = this.findProp(item, 'location.adress.place')
            return street.toLowerCase().includes(v)
                || number.toString().toLowerCase().includes(v)
                || place.toLowerCase().includes(v)
        },

        filterRating: function (item, v) {
            var rating = this.getRate(item.rating)
            return rating.toLowerCase().includes(v)
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
            if (this.nameQuery) {
                result = result.filter((item) => {
                    return this.nameQuery.toLowerCase().split(' ').every(v => this.filterName(item, v))
                })
            }
            if (this.locationQuery) {
                result = result.filter((item) => {
                    return this.locationQuery.toLowerCase().split(' ').every(v => this.filterLocation(item, v))
                })
            }
            if (this.ratingQuery) {
                var query = this.ratingQuery.toString()
                result = result.filter((item) => {
                    return query.toLowerCase().split(' ').every(v => this.filterRating(item, v))
                })
            }
            
            return result
        }
    }
});