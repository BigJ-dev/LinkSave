if (typeof require !== 'undefined') {
  require.extensions['.less'] = file => {};
}

const withLess = require('@zeit/next-less'),

  nextConfig = {
    //target: 'serverless',
    env: {
      weatherApi: '',
      mapBoxApi: ''
    },
    onDemandEntries: {
      maxInactiveAge: 1000 * 60 * 60,
      pagesBufferLength: 5
    },
    lessLoaderOptions: {
      javascriptEnabled: true
    },
    resolve: {
      extensions: ['.web.js', '.mjs', '.js', '.json', '.web.jsx', '.jsx', '.less'],
    },
    webpack: config => config
  };

module.exports = withLess(nextConfig);
