import React, { Component } from 'react'
import { withOktaAuth } from '@okta/okta-react'
import M from 'materialize-css'
import Logo from '../misc/Logo'
import TimesAgo from '../misc/TimesAgo'
import API from '../misc/api'

class JobView extends Component {
  state = {
    job: null
  }

  async componentDidMount() {
    const id = this.props.match.params.job_id

    API.get(`jobs/${id}`, {
      headers: {
        'Authorization': 'Bearer ' + await this.props.authState.accessToken.accessToken
      }
    })
      .then(response => {
        this.setState({
          job: response.data
        })
      })
      .catch(error => {
        console.log(error)
        M.toast({ html: error, classes: 'rounded' })
      })
  }

  render() {
    const { job } = this.state
    const jobInfo = job && (
      <div className="row" style={{ "marginTop": "30px" }}>
        <div className="col s12">
          <div className="row">
            <div className="col s3">
              <Logo logoUrl={job.logoUrl} />
            </div>
            <div className="col s3 offset-s6">
              <ul style={{ "margin": "0px" }}>
                <li><span>{job.company}</span></li>
                <li><span>{job.id}</span></li>
                <li><TimesAgo createDate={job.createDate} /></li>
              </ul>
            </div>
          </div>
          <div className="divider"></div>
          <div className="row" style={{ "marginTop": "30px", "marginBottom": "20px" }}>
            <div className="col s12">
              <span className="flow-text">{job.title}</span>
              <span className="waves-effect waves-light btn-small blue right">Apply</span>
            </div>
          </div>
          <div className="row">
            <div className="col s12">
              <span>{job.description}</span>
            </div>
          </div>
        </div>
      </div>
    )
    return (
      <div className="container">
        {jobInfo}
      </div>
    )
  }
}

export default withOktaAuth(JobView)