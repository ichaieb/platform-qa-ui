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
    casper.wait(8000);
    });
   }
  });

casper.then( function(){  
    casper.echo('Clicking user button');
    casper.click('div#UIUserPlatformToolBarPortlet a');
    casper.waitForSelector('i.uiIconPLFProfile');

  });

casper.then( function(){  
    casper.echo('Clicking userflags button');
    casper.click('i.uiIconFlags');
    casper.waitForSelector('div.MaskContainer');
    casper.wait(4000);
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });

casper.then( function(){
    casper.echo('Closing userflags popup');
    casper.click('div.uiAction.uiActionBorder a.btn:nth-of-type(2)');
    casper.echo('Clicking button');
    casper.wait(4000);
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });

}