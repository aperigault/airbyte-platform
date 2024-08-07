# {{ connectorName }}
This is a connector for {{connectorName}} contributed directly from the Connector Builder

{{ description }}

## Local Development
### Environment Setup
You will need `airbyte-ci` installed. You can find the documentation [here](airbyte-ci).

### Build
This will create a dev image (`{{connectorImageName}}:dev`) that you can use to test the connector locally.
```bash
airbyte-ci connectors --name={{connectorImageName}} build
```

### Test
This will run the acceptance tests for the connector.
```bash
airbyte-ci connectors --name={{connectorImageName}} test
```
