module.exports = {
  "id": "eXo Platform",
  "viewports": [
    {
      "label": "desktop",
      "width": 1500,
      "height": 768
    }
  ],
  "scenarios": [
    {
      "label": "Login_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 500,
      "hideSelectors": ["div#loginFooter"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div.uiLogin"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Home_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 500,
      "onReadyScript": "onReady_home.js",
      "hideSelectors": ["ul#suggestions li","div.currentDateContainer center a"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        ".LeftNavigationTDContainer.TDContainer",
        ".RightBodyTDContainer.TDContainer",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "td.OfficeMiddleTDContainer.TDContainer",
        "td.OfficeRightTDContainer.TDContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "AppsBtn",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_appsbtn.js",
      "hideSelectors": ["ul#suggestions li","div.currentDateContainer center a"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Apps_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "onBefore_appspage.js",
      "onReadyScript": "",
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
  /*  {
      "label": "Calendar_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onReadyScript": "onReady_calendar.js",
      "hideSelectors": [],
      "removeSelectors": ["div#UIMiniCalendar","div.eventWeekBar"],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },*/
    {
      "label": "AdduserBtn",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_adduserbtn.js",
      "readySelector": "",
      "delay": 1000,
      "hideSelectors": ["ul#suggestions li","div.currentDateContainer center a"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div.UITableColumnContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Community_page_adduser",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "onBeforeScript": "onBefore_adduserpage.js",
      "onReadyScript": "",
      "readySelector": "",
      "delay": 1000,
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div.UITableColumnContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "UserMgmntBtn",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_usermgmntbtn.js",
      "readySelector": "",
      "delay": 1000,
      "hideSelectors": ["ul#suggestions li","div.currentDateContainer center a"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div.UITableColumnContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "CommPage",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "onBeforeScript": "onBefore_usermgmntpage.js",
      "onReadyScript": "",
      "readySelector": "",
      "delay": 1000,
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div.UITableColumnContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "ContentBtn",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_contentbtn.js",
      "hideSelectors": ["ul#suggestions li","div.currentDateContainer center a"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div.UITableColumnContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Content_admin",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "onBefore_contentadminpage",
      "onReadyScript": "",
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div.UITableColumnContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "ContentMgmnt",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "onBeforeScript": "onBefore_contentMgmnt.js",
      "onReadyScript": "",
      "readySelector": "",
      "delay": 1000,
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div.UITableColumnContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Content_search_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "onBefore_contentsearch",
      "onReadyScript": "",
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Documents_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onReadyScript": "onBefore_document.js",
      "hideSelectors": [],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },


    {
      "label": "Documents_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onReadyScript": "onBefore_docs.js",
      "hideSelectors": [],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Forums_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_forums_page.js",
      "hideSelectors": ['div#UICategoryInfo'],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Network_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_network_page.js",
      "hideSelectors": [],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "PortalBtn",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_portalbtn.js",
      "hideSelectors": ["ul#suggestions li","div.currentDateContainer center a"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div.UITableColumnContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Portal_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "onBefore_portalpage.js",
      "onReadyScript": "",
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div.UITableColumnContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Portal_gpsites_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "onBefore_portalgpsitespage.js",
      "onReadyScript": "",
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Portal_notifs_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "onBefore_portalnotifspage.js",
      "onReadyScript": "",
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Portal_sites_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "onBefore_portalsitespage.js",
      "onReadyScript": "",
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Portalpages_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl":process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "onBefore_portalpages.js",
      "onReadyScript": "",
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Searchindexing_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "onBefore_searchindexinpage.js",
      "onReadyScript": "",
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Spaces_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_spacespage.js",
      "hideSelectors": [],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "System_mngmnt_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "onBefore_systemmgmentpage.js",
      "onReadyScript": "",
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "System_monitoring",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "onBefore_systemmonitoringpage.js",
      "onReadyScript": "",
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Tasks_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_taskspage.js",
      "hideSelectors": [],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "User_page_acts",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_usrpage_acts.js",
      "hideSelectors": [],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "User_page_dashboard",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_usrpagedashbrd.js",
      "hideSelectors": [],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "User_page_flags",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_usrpageflags.js",
      "hideSelectors": [],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer",
        "div.MaskContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Close_flags",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_closeflags.js",
      "hideSelectors": ["ul#suggestions li","div.currentDateContainer center a"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "User_page_network",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_usrpagenetwork.js",
      "hideSelectors": [],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "User_page_notifs",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_usrpagenotifs.js",
      "hideSelectors": [],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "User_page_profile",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "onBefore_usrpageprofile.js",
      "onReadyScript": "",
      "hideSelectors": [],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "User_page_settings",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_usrpagesettings.js",
      "hideSelectors": [],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer",
        "div.MaskContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Close_settings",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_closeusersettings.js",
      "hideSelectors": ["ul#suggestions li","div.currentDateContainer center a"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "WebconfBtn",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_webconfbtn.js",
      "hideSelectors": ["ul#suggestions li","div.currentDateContainer center a"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Webconf_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "onBefore_webconfpage.js",
      "onReadyScript": "",
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "User_page_wiki",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": process.argv[process.argv.length-1],
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_usrpagewiki.js",
      "hideSelectors": ["div.uiWikiPageInfoArea"],
      "removeSelectors": [],
      "hoverSelector": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    }
  ],
  "paths": {
    "bitmaps_reference": "backstop_data_desktop/bitmaps_reference",
    "bitmaps_test": "backstop_data_desktop/bitmaps_test",
    "engine_scripts": "backstop_data_desktop/engine_scripts",
    "html_report": "backstop_data_desktop/html_report",
    "ci_report": "backstop_data_desktop/ci_report"
  },
  "report": ["browser", "CI"],
  "engine": "phantomjs",
  "engineFlags": [],
  "asyncCaptureLimit": 5,
  "asyncCompareLimit": 50,
  "debug": false,
  "debugWindow": false,
  "casperflags":[],
  "cliExitOnFail":true
}
