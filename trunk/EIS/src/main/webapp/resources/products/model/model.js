Ext.define('ProductsModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'name',  type: 'string'},
        {name: 'type',   type: 'string'},
        {name: 'org', type: 'string'},
        {name: 'program', type: 'string'},
        {name: 'state', type: 'string'},
        {name: 'currentState', type: 'string'},
        {name: 'country', type: 'string'}
    ]
});
Ext.create('Ext.data.Store', {
	model: 'ProductsModel',
	storeId: 'productsStore',
	data: {'items': [
	    {'name': 'Product 1', 'type': 'Operating System', 'org': 'Microsoft', 'program': 'Windows', 'state' : 'normal', 'currentState': 'normal', 'country': 'USA'},
   		{'name': 'Product 2', 'type': 'Word Editor', 'org': 'Microsoft', 'program': 'Office', 'state' : 'alert', 'currentState': 'alert', 'country': 'USA'},
   		{'name': 'Product 3', 'type': 'Web Browser', 'org': 'Netscape', 'program': 'Firefox', 'state' : 'normal', 'currentState': 'normal', 'country': 'MEX'}
	]},
	proxy: {
		type: 'memory',
		reader: {
			type: 'json',
			root: 'items'
		}
	}
});