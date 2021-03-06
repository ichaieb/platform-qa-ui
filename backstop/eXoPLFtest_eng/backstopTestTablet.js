module.exports = {
  "id": "eXo PLF Tablet",
  "viewports": [
    {
      "label": "tablet",
      "width": 1024,
      "height": 768
    }
  ],

  "scenarios": [
    {
      "label": "Login_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
        "div#NavigationPortlet.UIContainer div.TRContainer.clearfix",
        "div#RightBody.UIContainer"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Answers_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onReadyScript": "onBefore_answers.js",
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
      "label": "AppsBtn",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
        "viewport"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Apps_page",
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/administration/registry",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/administration/registry",
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
    {
      "label": "Calendar_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onReadyScript": "onReady_calendar.js",
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
      "label": "ChatButton",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
      "readyEvent": null,
      "readySelector": "",
      "onBeforeScript": "",
      "onReadyScript": "onReady_chatbtn.js",
      "delay": 1000,
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
      "label": "Chat_page",
      "url": "http://qa-ui03.exoplatform.org/portal/intranet/chat",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/intranet/chat",
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "hideSelectors": ["div.currentDateContainer center"],
      "removeSelectors": [],
      "hoverSelector": "",
      "onBeforeScript": "onBefore_chatpage.js",
      "onReadyScript": "",
      "clickSelector": "",
      "postInteractionWait": "",
      "selectors": [
        "viewport",
        "document",        
        "div.uiGrayLightBox.clearfix.input-prepend.no-user-selection",
        "div.uiLeftContainerArea.no-user-selection"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "AdduserBtn",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
        "viewport"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Community_page_adduser",
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/administration/newStaff",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/administration/newStaff",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
        "viewport"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "CommPage",
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/administration/management",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/administration/management",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
        "viewport"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Content_admin",
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:web-contributors/wcmAdmin",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:web-contributors/wcmAdmin",
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
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:web-contributors/siteExplorer",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:web-contributors/siteExplorer",
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
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/search",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/search",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_forums_page.js",
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
      "label": "Network_page",
      "url": process.argv[process.argv.length-1],
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
        "viewport"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Portal_page",
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/branding",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/branding",
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
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/groupnavigation",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/groupnavigation",
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
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/notification",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/notification",
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
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/portalnavigation",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/portalnavigation",
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
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/administration/pageManagement",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/administration/pageManagement",
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
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/searchIndexing",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/searchIndexing",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/servicesManagement",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/servicesManagement",
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
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/monitoring",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/monitoring",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_usrpageflags.js",
      "hideSelectors": ["ul#suggestions li","div.currentDateContainer center a"],
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_Closeflags.js",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_usrpageprofile.js",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_usrpagesettings.js",
      "hideSelectors": ["ul#suggestions li","div.currentDateContainer center a"],
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
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
        "viewport"
      ],
      "selectorExpansion": true,
      "misMatchThreshold" : 0,
      "requireSameDimensions": true
    },
    {
      "label": "Webconf_page",
      "url": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/webconferencing",
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/g/:platform:administrators/webconferencing",
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
      "referenceUrl": "http://qa-ui03.exoplatform.org/portal/login?initialURI=%2Fportal%2Fintranet%2F",
      "readyEvent": null,
      "readySelector": "",
      "delay": 1000,
      "onBeforeScript": "",
      "onReadyScript": "onBefore_usrpagewiki.js",
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
    }
  ],
  "paths": {
    "bitmaps_reference": "backstop_data_tablet/bitmaps_reference",
    "bitmaps_test": "backstop_data_tablet/bitmaps_test",
    "engine_scripts": "backstop_data_tablet/engine_scripts",
    "html_report": "backstop_data_tablet/html_report",
    "ci_report": "backstop_data_tablet/ci_report"
  },
  "report": ["browser", "CI"],
  "engine": "phantomjs",
  "engineFlags": [],
  "asyncCaptureLimit": 5,
  "asyncCompareLimit": 50,
  "debug": false,
  "debugWindow": false,
  "casperflags":[],
  "cliExitOnFail":false,
  "port":3001
}