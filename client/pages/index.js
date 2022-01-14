import Head from 'next/head';
import * as fetch from 'node-fetch';
import Overview from '../components/Overview';

const OverviewPage = ({data}) => (
  <>
    <Head>
      <link rel="stylesheet" href="/static/react-vis.css" />
    </Head>
    <Overview data={data}/>
  </>
);

export async function getServerSideProps() {
    const res = await fetch(`http://host.docker.internal:8080/api/link/user/getUserLinks`)
    const data = await res.json()
    console.log(data)
    return {
        props: {
            data ,
        },
    }
}

export default OverviewPage;
