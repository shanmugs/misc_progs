import { faArrowRight } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { useEffect, useRef,useState } from "react"
import { useDispatch,useSelector } from "react-redux"
import Layout from "../../components/layout/layout"
import { SET_NAVBAR_TITLE} from "../../state/actions"
import styles from '../../styles/Auth.module.scss'
import Link from 'next/link'
import ProgressIndicator from '../../components/progress_indicator/progress_indicator'
import { EMAIL_REQUIRED, INVALID_EMAIL } from "../../consts/errors";
import {forgotPassword  } from "../../external_api/users/index";
import { isEmailValid} from '../../utils/validators';
import { FORGOT_PASSWORD } from "../../consts/navbar_titles"
import {useRouter} from 'next/router'
import { useAuthRedirect, usePreFetch } from "../../utils/hooks"

const ForgotPassword = ()=>{

    const dispatch = useDispatch()

    const router = useRouter()

    const {currentActivity,jwt,isLoggedIn} = useSelector(state => state)

    const [loadingScreen,setScreenLoad] = useState(true)
    
    useEffect(()=>{
        dispatch({type:SET_NAVBAR_TITLE,payload: FORGOT_PASSWORD})

        usePreFetch(currentActivity,router)
        
        useAuthRedirect(isLoggedIn,jwt,router,setScreenLoad)
    },[])

    const emailInputRef = useRef(null)

    const [error, setError] = useState('')

    const [isProcessing,setIsProcessing] = useState(false)

    const [isSubmitBtnEnabled, setSubmitBtnEnabledState] = useState(false)
    
    const [successMsg,setSuccessMsg] = useState('')

    const formSubmittedHandler = async (event) =>{

        event.preventDefault()

        setError('')

        setSubmitBtnEnabledState(true)

        const email = emailInputRef.current.value;

        if(validateInput(email) === false){ 
            console.log(!validateInput(email)) 
           return setSubmitBtnEnabledState(false)
        }

        setIsProcessing(true)

        const {errorMsg,result} = await forgotPassword(email)
        if(errorMsg){
            setIsProcessing(false)
            setSubmitBtnEnabledState(false)
            return setError(errorMsg)
        }
        setIsProcessing(false)
        setSubmitBtnEnabledState(false)
        setSuccessMsg(result)

    }

    const validateInput = (email) =>{

        if(!email?.trim()){
             setError(EMAIL_REQUIRED)
             return false
        }

        if(!isEmailValid(email)){
            setError(INVALID_EMAIL)
            return false
        }
    }

    return (
        <>
        {
            loadingScreen?
            <ProgressIndicator/>:
            <Layout title='Forgot password'>

            <section className={styles.main}>
            {successMsg?
                <div className={styles.success_message}>
                    <p>{successMsg}</p>
                </div>:
           <>
                <div className={error? styles.error:styles.noerror}>{error?error:''}</div>
                <form onSubmit={formSubmittedHandler}>
                    <label htmlFor='email'>Enter email to reset password</label>
                    <input type='email' id='email' ref={emailInputRef}/>
                    
                    <div className={styles.group1}>
                        <p>Forgot password</p>
                        <button type='submit' disabled={isSubmitBtnEnabled}>
                            {isProcessing?
                                <ProgressIndicator min={true}/>:
                                <FontAwesomeIcon icon={faArrowRight}/>
                            }
                        </button>
                    </div>
                    <div className={styles.group2}>
                        <Link href='/auth/signin'><p>Sign in</p></Link>
                    </div>
                </form>
            </>
                }
            </section>

        </Layout>
   
        }
        </>
       
   )
}

export default ForgotPassword