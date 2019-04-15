module.exports = function(casper, scenario, vp) {
  // Example: Adding script delays to allow for things like CSS transitions to complete.
  casper.thenOpen(scenario.url);

casper.then( function(){
    casper.echo('loggin');
    if (this.exists('div.loginButton')) {
    casper.waitForSelector('form', function(){
      this.fillSelectors('form', {
        'input[id="username"]':'root',
        'input[id="password"]':'gtn'
      }, true);
    casper.echo('Clicking button');
    casper.click('button.button');
    casper.waitForSelector('div.uiBox.uiSuggestions');
    casper.wait(8000);
    });
   }
  });

casper.then( function(){  
    casper.echo('Waiting for page to load');
    casper.waitForSelector('div.uiBox.uiSuggestions');
    casper.wait(8000);    
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');
  });

}