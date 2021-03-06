import {
    Archive,
    Edit,
    MoreHorizontal,
    Printer,
    Save,
    Trash,
    Link
  } from 'react-feather';
  import {
    Card,
    Col,
    DatePicker,
    Dropdown,
    Menu,
    Message,
    Row,
    Input,
    Button,
    Form
  } from 'antd';
  import Axios from 'axios';
  import * as fetch from 'node-fetch';
  import Router from 'next/router'
  import PostCard from '../components/shared/PostCard';
  import StatCard from '../components/shared/StatCard';
  import { theme } from '../components/styles/GlobalStyles';
  import { useEffect, useState } from 'react';
  
  
  const axes = Array.from(Array(12).keys());
  
  const menu = (
    <Menu>
      <Menu.Item>
        <Row type="flex" align="middle">
          <Archive size={16} strokeWidth={1} className="mr-3" />{' '}
          <span>Archive</span>
        </Row>
      </Menu.Item>
      <Menu.Item>
        <Row type="flex" align="middle">
          <Edit size={16} strokeWidth={1} className="mr-3" /> <span>Edit</span>
        </Row>
      </Menu.Item>
      <Menu.Item>
        <Row type="flex" align="middle">
          <Trash size={16} strokeWidth={1} className="mr-3" /> <span>Delete</span>
        </Row>
      </Menu.Item>
      <Menu.Divider />
      <Menu.Item>
        <Row type="flex" align="middle">
          <Save size={16} strokeWidth={1} className="mr-3" /> <span>Save as</span>
        </Row>
      </Menu.Item>
      <Menu.Item>
        <Row type="flex" align="middle">
          <Printer size={16} strokeWidth={1} className="mr-3" />{' '}
          <span>Print</span>
        </Row>
      </Menu.Item>
    </Menu>
  );
  
  
  const Overview = ({data}) => {
   
    const [apiData,setApiData] = useState({
       siteName: "",
       siteUrl: ""
    })
  
    function submit(e){
      Axios.post("http://host.docker.internal:8080/api/link/user/addLink",{
        siteName: apiData.siteName,
        siteUrl: apiData.siteUrl
      })
      .then(res=>{
        console.log(res.apiData)
      })
      Router.reload(window.location.pathname);
    }
  
    function updateUserLink(){
      if(apiData.id != null){
        Axios.put(`http://host.docker.internal:8080/api/link/user/updateUserLink/${apiData.id}`,{
          siteName: apiData.siteName,
          siteUrl: apiData.siteUrl
        })
        .then(res=>{
          setApiData(res.data)
          console.log(res.data)
        })
        Router.replace(Router.asPath);
      }else{
        alert("Please select the link you want to Edit or add a link")
      }
   
    }
  
    function clearInput(){
        setApiData('')
    }
  
    function getUserLinkAndSetOnInput(userLink){
        Axios.get(`http://host.docker.internal:8080/api/link/user/getUserLink/${userLink.id}`)
        .then(res=>{
          if(res.data !=null){
            setApiData(userLink)
            console.log(userLink)
          }
        })
    }
  
    function deleteUserLink(linkId,siteName){
      if (window.confirm(`Delete the item "${siteName}" ?`)) {
        Axios.delete(`http://host.docker.internal:8080/api/link/user/deleteUserLink/${linkId}`)
        .then(res=>{
          if(res.apiData !=null){
            //  alert(`The siteLink: ${siteName} has been deleted`)
          }
        })
        Router.replace(Router.asPath);
      }
    }
  
    function handle(e){
      const newdata={...apiData}
      newdata[e.target.id] = e.target.value
      setApiData(newdata)
      console.log(newdata)
  
    }
  
    return (
      <div>
        <Card
          title="Welcome Tshepo.."
          extra={
            <Dropdown overlay={menu}>
              <MoreHorizontal size={20} strokeWidth={1} fill={theme.errorColor} />
            </Dropdown>
          }
          bodyStyle={{ padding: '1rem' }}
          className="mb-4"
        >
        <Form onSubmit={(e)=> submit(e)}>
         <Form.Item>
         <Input
            onChange={(e)=>handle(e)}
            id="siteName"
            value={apiData.siteName}
            placeholder='Site Name'
            required
            style={{ width: 250 }}
           ></Input>
         </Form.Item>
       
         <Form.Item>
         <Input
            onChange={(e)=>handle(e)}
            id="siteUrl"
            value={apiData.siteUrl}
            placeholder='Site Url'
            required
            style={{ width: 250 }}
           ></Input>
         </Form.Item>
         
         <Form.Item>
           <Row>
             <Button  style={{ width: 119 , marginRight: '10px' }} type="primary" htmlType="submit">Add Site</Button> 
             <Button  onClick={()=> updateUserLink()} style={{ width: 119 }} type="primary">Update Site</Button> 
           </Row>
         </Form.Item>
         <Form.Item>
           <Button onClick={()=> clearInput()} danger="true" style={{ width: 250 }} type="dashed">Clear Input</Button>  
         </Form.Item>
        </Form>  
        </Card>
  
        <Card
          title="Your website links"
          extra={
            <Dropdown overlay={menu}>
              <MoreHorizontal size={20} strokeWidth={1} fill={theme.textColor} />
            </Dropdown>
          }
          bodyStyle={{ padding: '1rem' }}
          className="mb-4"
        >
        {data.map(userLink => {
          return (
          <Col key={userLink.id} xs={50} sm={50} md={50}>
            <StatCard
              type="fill"
              siteName={userLink.siteName}
              siteUrl={userLink.siteUrl}
              icon={<Link size={20} strokeWidth={1} />}
              iconEdit={<Edit size={20} strokeWidth={1} />}
              iconTrash={<Trash size={20} strokeWidth={1} />}
              color={theme.darkColor}
              clickHandlerVist={() => window.open(userLink.siteUrl)}
              clickHandlerEdit={() => getUserLinkAndSetOnInput(userLink)}
              clickHandlerDelete={() => deleteUserLink(userLink.id,userLink.siteName)}
             />
           </Col>
          )
         })}
        </Card>
      </div>
    )
  }
  
  export default Overview; 

