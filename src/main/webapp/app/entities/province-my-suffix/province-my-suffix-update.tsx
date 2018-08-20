import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './province-my-suffix.reducer';
import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IProvinceMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IProvinceMySuffixUpdateState {
  isNew: boolean;
}

export class ProvinceMySuffixUpdate extends React.Component<IProvinceMySuffixUpdateProps, IProvinceMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { provinceEntity } = this.props;
      const entity = {
        ...provinceEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/province-my-suffix');
  };

  render() {
    const { provinceEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vtgApp.province.home.createOrEditLabel">
              <Translate contentKey="vtgApp.province.home.createOrEditLabel">Create or edit a Province</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : provinceEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="province-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameLabel" for="name">
                    <Translate contentKey="vtgApp.province.name">Name</Translate>
                  </Label>
                  <AvField id="province-my-suffix-name" type="text" name="name" />
                </AvGroup>
                <AvGroup>
                  <Label id="sloganLabel" for="slogan">
                    <Translate contentKey="vtgApp.province.slogan">Slogan</Translate>
                  </Label>
                  <AvField id="province-my-suffix-slogan" type="text" name="slogan" />
                </AvGroup>
                <AvGroup>
                  <Label id="introductionLabel" for="introduction">
                    <Translate contentKey="vtgApp.province.introduction">Introduction</Translate>
                  </Label>
                  <AvField id="province-my-suffix-introduction" type="text" name="introduction" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath1Label" for="filePath1">
                    <Translate contentKey="vtgApp.province.filePath1">File Path 1</Translate>
                  </Label>
                  <AvField id="province-my-suffix-filePath1" type="text" name="filePath1" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath2Label" for="filePath2">
                    <Translate contentKey="vtgApp.province.filePath2">File Path 2</Translate>
                  </Label>
                  <AvField id="province-my-suffix-filePath2" type="text" name="filePath2" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath3Label" for="filePath3">
                    <Translate contentKey="vtgApp.province.filePath3">File Path 3</Translate>
                  </Label>
                  <AvField id="province-my-suffix-filePath3" type="text" name="filePath3" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath4Label" for="filePath4">
                    <Translate contentKey="vtgApp.province.filePath4">File Path 4</Translate>
                  </Label>
                  <AvField id="province-my-suffix-filePath4" type="text" name="filePath4" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath5Label" for="filePath5">
                    <Translate contentKey="vtgApp.province.filePath5">File Path 5</Translate>
                  </Label>
                  <AvField id="province-my-suffix-filePath5" type="text" name="filePath5" />
                </AvGroup>
                <AvGroup>
                  <Label id="commentIdLabel" for="commentId">
                    <Translate contentKey="vtgApp.province.commentId">Comment Id</Translate>
                  </Label>
                  <AvField id="province-my-suffix-commentId" type="number" className="form-control" name="commentId" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/province-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  provinceEntity: storeState.province.entity,
  loading: storeState.province.loading,
  updating: storeState.province.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProvinceMySuffixUpdate);
