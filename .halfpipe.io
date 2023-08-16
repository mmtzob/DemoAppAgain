team: oscar
pipeline: demo-app
feature_toggles:
- update-pipeline
triggers:
- type: git
  uri: git@github.com:springernature/DemoApp.git
  shallow: true
tasks:
- type: docker-compose
  name: build
  save_artifacts:
  - build/distributions
  save_artifacts_on_failure:
  - build/reports
- type: deploy-cf
  name: deploy
  api: ((cloudfoundry.api-snpaas))
  org: ((cloudfoundry.org-snpaas))
  username: ((cloudfoundry.username-snpaas))
  password: ((cloudfoundry.password-snpaas))
  space: oscar-qa
  manifest: manifest.yml
  deploy_artifact: build/distributions/DemoApp-1.0-SNAPSHOT.zip