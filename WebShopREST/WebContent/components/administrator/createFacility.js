Vue.component("create-facility", {
	data: function () {
		    return {
		      title: "Create Sport Facility",
		      value: "Create",
		      product: {id: '', name:null, type:{id:0 , name:null },logoLocation:null, recreationTypes:[], status: null, workRange: null, location:{latitude: null,  longitude: null, adress: {street: null, number: null, place: null, shipingCode: null}}},
		      facilityTypes: null,
		      recreationTypes: null,
		      selectedRecreation: null,
		      managers : null,
		      selectedManager : null,
		      managersBool : null,
		      pr: "",
		      manager : {id: '', name: null, surname:null, username:null,password:null, gender:null,dateOfBirth:null, role:'Manager', facilities: []}
		    }
	},
	template: `
	<div>
		<div style="display: flex; justify-content: center;">
                    <h3>{{title}}</h3><br>
		<table style="position: absolute; top:30%; left: 55%; transform: translate(-50%, -50%);">
			<tr><td style="padding:16px">Name</td><td style="padding:16px"><input class="form-control" type = "text" v-model = "product.name" name = "name"></td></tr>
			<tr>
				<td style="padding:16px">Type</td>
				<td style="padding:16px">
					<select class="form-control" v-model = "product.type">
						<option v-for="(f, index) in facilityTypes" :value = "f.id">{{f.name}}</option>
					</select>
				</td>
			</tr>
			<tr><td style="padding:16px">Status</td><td style="padding:16px"><input type = "checkbox" v-model = "product.status" name = "status"></td></tr>
			<tr><td style="padding:16px">Working Hours</td><td style="padding:16px"><input class="form-control" type = "text" v-model = "product.workRange" name = "workRange"></td></tr>
			<tr><td style="padding:16px">Logo</td><td style="padding:16px"><input class="form-control" type="file" id="avatar" name="avatar" accept="image/png, image/jpeg"></td></tr>
			<tr><td colspan="2" style="padding:16px">Pick location on map:</td></tr>
			</table>
			<div style="margin-left: 18%;">
			<div id="map" style="width: 448px; height: 140px; position: absolute; top:65%; left: 55%; transform: translate(-50%, -50%);"></div>
			<div id="popup" class="ol-popup">
				<a href="#" id="popup-closer" class="ol-popup-closer"></a>
				<div id="popup-content"></div>
			</div>
			</div>
			<table style="position: absolute; top:136%; left: 55%; transform: translate(-50%, -50%); width:480px;">
			<tr><td style="padding:16px">Location</td><td style="padding:16px"><input class="form-control" type = "text" v-model = "pr" name = "location" id = "location" style="width: 320px" disabled></td></tr>
			<tr><td style="padding:16px">Street</td><td style="padding:16px"><input class="form-control" type = "text" v-model = "product.location.adress.street" name = "street"></td></tr>
			<tr><td style="padding:16px">Number</td><td style="padding:16px"><input class="form-control" type = "number" v-model = "product.location.adress.number" name = "number"></td></tr>
			<tr><td style="padding:16px">City</td><td style="padding:16px"><input class="form-control" type = "text" v-model = "product.location.adress.place" name = "number"></td></tr>
			<tr><td style="padding:16px">Zip code</td><td style="padding:16px"><input class="form-control" type = "number" v-model = "product.location.adress.shipingCode" name = "number"></td></tr>
			<template v-if="managersBool">
			<tr>
				<td style="padding:16px">Managers</td>
				<td style="padding:16px">
					<select class="form-control" v-model = "selectedManager">
						<option v-for="(f, index) in managers" :value = "f.id">{{f.name}} {{f.surname}}</option>
					</select>
				</td>
			</tr>
			</template>
			<template v-else>
			<tr><td colspan="2" style="padding:16px">Make new Manager:</td></tr>
			<tr><td style="padding:16px">Username</td><td style="padding:16px"><input class="form-control" type="text" name="username" v-model="manager.username"></td></tr>
			<tr><td style="padding:16px">Password</td><td style="padding:16px"><input class="form-control" type="password" name="password" v-model="manager.password"></td></tr>
			<tr><td style="padding:16px">Name</td><td style="padding:16px"><input class="form-control" type="text" name="name" v-model="manager.name"></td></tr>
			<tr><td style="padding:16px">Surname</td><td style="padding:16px"><input class="form-control" type="text" name="date" v-model="manager.surname"></td></tr>
			<tr><td style="padding:16px">Birthday Date:</td><td style="padding:16px"><input class="form-control" type="date" v-model="manager.dateOfBirth" name="birthday"></td></tr>
			<tr><td style="padding:16px">Gender</td><td style="padding:16px">
			<select class="form-control" v-model="manager.gender">
  				<option disabled value="">Please select one</option>
  				<option>Male</option>
  				<option>Female</option>
  				<option>Other</option>
			</select></td></tr>
			</template>
			<tr><td colspan="2" style="padding:16px"><button class="w-100 btn btn-lg btn-dark" v-on:click = "createFacility">Create Facility</button></td></tr>
		</table>
	</div>
	</div>
		
	`
	, 
	methods : {
		createFacility : function () {
			var loc = document.getElementById('location');
			var position = loc.value.split(",");
			this.product.location.latitude = position[0];
			this.product.location.longitude = position[1];
			var input = document.getElementById("avatar");
			var file = input.value.split("\\");
			var fileName = file[file.length-1];
			this.product.logoLocation = fileName;
			event.preventDefault();
			axios.post('rest/facility/createandreturn', this.product).
			then(response => {
				this.recreationTypes = response.data;
				if(!this.managersBool)
				{
					axios.post('rest/managers/createandreturn/',this.manager)
					.then(response => {
					this.selectedManager = response.data;
					axios.post('rest/managers/updatefacility/' + this.selectedManager + '&' + this.recreationTypes)
					.then(alert("Created successfully!"))
					.catch((e) => { alert("Exception")})
					})
					.catch((e) => { alert("Exception")})
				}
				else
				{
					axios.post('rest/managers/updatefacility/' + this.selectedManager + '&' + this.recreationTypes)
					.then(alert("Created successfully!"))
					.catch((e) => { alert("Exception")})
				}			
			});
			}

	},
	mounted () {
		instantiateMap()
		try {
			axios.get('rest/facilitytypes/')
			.then(response => (this.facilityTypes = response.data))
			
			axios.get('rest/managers/empty')
			.then(response => {
				this.managers = response.data
				if(this.managers.length == 0)
				{
					this.managersBool = false;
				}
				else
				{
					this.managersBool = true;
				}
			})
		}
		catch(error)
		{
			console.log(error);
		}
    }
});

function instantiateMap() {
	var attribution = new ol.control.Attribution({
		collapsible: false
	});

	var position = [19.833549,45.267136]
	var location = "Default Marker Location"

	var map = new ol.Map({
		controls: ol.control.defaults({ attribution: false }).extend([attribution]),
		layers: [
			new ol.layer.Tile({
				source: new ol.source.OSM()
			})
		],
		target: 'map',
		view: new ol.View({
			center: ol.proj.fromLonLat(position),
			maxZoom: 18,
			zoom: 9
		})
	});

	var layer = new ol.layer.Vector({
		source: new ol.source.Vector({
			features: [
				new ol.Feature({
					geometry: new ol.geom.Point(ol.proj.fromLonLat(position))
				})
			]
		})
	});

	map.addLayer(layer);

	var container = document.getElementById('popup');
	var content = document.getElementById('popup-content');
	var closer = document.getElementById('popup-closer');

	var overlay = new ol.Overlay({
		element: container,
		autoPan: true,
		autoPanAnimation: {
			duration: 250
		}
	});
	//map.addOverlay(overlay);

	/*closer.onclick = function f() {
		overlay.setPosition(undefined);
		closer.blur();
		return false;
	};*/

	map.on('singleclick', function (event) {
		if (map.hasFeatureAtPixel(event.pixel) === true) {
			var coordinate = event.coordinate;

			content.innerHTML = '<b>' + location + '</b>';
			overlay.setPosition(coordinate);
		} else {
			overlay.setPosition(undefined);
			closer.blur();
		}
	});

	content.innerHTML = '<b>' + location + '</b>';
	overlay.setPosition(ol.proj.fromLonLat(position));

	map.on('dblclick', function(evt)
// map.on('singleclick', function(evt)
{
    var coordinatePretty = ol.coordinate.toStringHDMS(ol.proj.transform(evt.coordinate, 'EPSG:3857', 'EPSG:4326'), 2);
    var coordinate = ol.proj.toLonLat(evt.coordinate);

    console.log("Clicked at position: ", coordinatePretty, coordinate);
    console.log("Clicked at position: ", evt.coordinate);

    // Clear markers source
    //MapSource.clear();

    // Add point
    var f = new ol.Feature({
        // From lon, lat
        // new ol.geom.Point(ol.proj.fromLonLat([4.35247, 50.84673])),
        // From event
        geometry: new ol.geom.Point(evt.coordinate),
        name: 'Marker text',
        desc: '<label>Details</label> <br> Latitude: ' + coordinate[1].toFixed(6) + ' Longitude: ' + coordinate[0].toFixed(6)
    });
    //f.setStyle(iconStyle);

    // Add to source
    //MapSource.addFeature(f);

    // Animate marker position
    //AnimatePoint(f);

    // Set div coordinates
    //SetDivLonLat(coordinate[0].toFixed(6), coordinate[1].toFixed(6));
    
    var lon = coordinate[0].toFixed(6);
    var lat = coordinate[1].toFixed(6);
    var loc = document.getElementById('location');
    loc.value = lon + ',' + lat;
    
    position = [lon,lat];
    
    
    var layer = new ol.layer.Vector({
		source: new ol.source.Vector({
			features: [
				new ol.Feature({
					geometry: new ol.geom.Point(ol.proj.fromLonLat(position))
				})
			]
		})
	});
	map.addLayer(layer);
	
	

    // Get lon, lat
    // var coordinate = PointToLonLat(evt);
    // Show popup
    // PopUp(coordinate[0], coordinate[1]);
});
}