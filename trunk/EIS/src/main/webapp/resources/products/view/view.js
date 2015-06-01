Ext.require(['*']);

Ext.onReady(function() {
    
    Ext.create('Ext.Viewport', {
        layout: {
            type: 'border',
            padding: 5
        },
        defaults: {
            split: true
        },
        items: [{
            region: 'north',
            split: true,
            height: 100,
            minHeight: 60
        },{
            region: 'west',
            collapsible: true,
            layout: 'absolute',
            split: true,
            width: '30%',
            minWidth: 100,
            minHeight: 140,
            bodyPadding: 10
        },{
            region: 'center',
            html: 'center center',
            title: 'Results',
            minHeight: 80,
            items: [{
            	xtype: 'array-grid',
            	store: 'productsStore',
            	viewConfig: {
            		stripeRows: true,
            		enableTextSelection: true
            	},
            	columns: [{
            		text: 'Product Name',
            		flex: 1,
            		sortable: true,
            		dataIndex: 'name'
            	},{
            		text: 'Type',
            		dataIndex: 'type'
            	},{
            		text: 'Org',
            		dataIndex: 'org'
            	},{
            		text: 'Program Name',
            		flex: 1,
            		sortable: true,
            		dataIndex: 'program'
            	},{
            		text: 'State',
            		dataIndex: 'state'
            	},{
            		text: 'Current State',
            		sortable: true,
            		dataIndex: 'currentState'
            	},{
            		text: 'Country',
            		sortable: true,
            		dataIndex: 'country'
            	}]
            }]
        }]
    }); //End viewport
});