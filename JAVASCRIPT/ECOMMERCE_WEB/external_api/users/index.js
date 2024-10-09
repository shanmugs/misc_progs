import axios from '../../axios/index'
import { getAxiosErrorMessage } from '../../utils/object_property_pickers';

const isJwtValid  = async ( jwt ) =>{
  
    try{
      const response = await axios.post(`/users/checktokenexpiry`,{token:jwt});
      return response.status === 200
    }catch(error){
      return false
    }
}

const signIn = async (email,password) =>{
  let result, errorMsg;
  
  try{
    const response = await axios.post(`/users/signin`,{email,password});
    result = response.data.data;

  }catch(error){
      errorMsg = getAxiosErrorMessage(error);
  }

  return {errorMsg,result}

}

const signUp = async (name,email,password) =>{
  let result, errorMsg;
  
  try{
    const response = await axios.post(`/users/signup`,{name,email,password});
    result = response.data.data;

  }catch(error){
      errorMsg = getAxiosErrorMessage(error);
  }

  return {errorMsg,result}

}

const forgotPassword = async (email) =>{
  let result, errorMsg;
  
  try{
    const response = await axios.post(`/users/forgotpassword`,{email});
    result = response.data.message;

  }catch(error){
      errorMsg = getAxiosErrorMessage(error);
  }

  return {errorMsg,result}

}

const resetPassword = async (token,password) =>{
  let result, errorMsg;
  
  try{
    const response = await axios.post(`/users/resetPassword/${token}`,{password});
    result = response.data.message;

  }catch(error){
      errorMsg = getAxiosErrorMessage(error);
  }

  return {errorMsg,result}

}

const updateName = async (newName,userId,jwt) =>{

  let result, errorMsg;
  
  try{
    axios.defaults.headers['authorization'] = `Bearer ${jwt}`;

    const response = await axios.patch(`/users/updatename/${userId}/`,{name:newName});
    result = response.data.data;

  }catch(error){
      errorMsg = getAxiosErrorMessage(error);
  }

  return {errorMsg,result}

}

const updateEmail = async (newEmail,userId,jwt) =>{

  let result, errorMsg;
  
  try{
    axios.defaults.headers['authorization'] = `Bearer ${jwt}`;

    const response = await axios.patch(`/users/updatemail/${userId}/`,{email:newEmail});
    result = response.data.data;

  }catch(error){
      errorMsg = getAxiosErrorMessage(error);
  }

  return {errorMsg,result}
}

const googleAuth = async(name, email,appSecret) =>{
  let result, errorMsg;

  try{
    const response = await axios.post(`/users/google/frontendsucess/${appSecret}`,{name,email});
    result = response.data.data;

  }catch(error){
      errorMsg = getAxiosErrorMessage(error);
  }

  return {errorMsg,result}
}

export {isJwtValid,signIn,signUp,forgotPassword,resetPassword,
          updateName, updateEmail, googleAuth}