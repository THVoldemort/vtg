import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './province-my-suffix.reducer';
import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProvinceMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class ProvinceMySuffixDetail extends React.Component<IProvinceMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { provinceEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vtgApp.province.detail.title">Province</Translate> [<b>{provinceEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">
                <Translate contentKey="vtgApp.province.name">Name</Translate>
              </span>
            </dt>
            <dd>{provinceEntity.name}</dd>
            <dt>
              <span id="slogan">
                <Translate contentKey="vtgApp.province.slogan">Slogan</Translate>
              </span>
            </dt>
            <dd>{provinceEntity.slogan}</dd>
            <dt>
              <span id="introduction">
                <Translate contentKey="vtgApp.province.introduction">Introduction</Translate>
              </span>
            </dt>
            <dd>{provinceEntity.introduction}</dd>
            <dt>
              <span id="filePath1">
                <Translate contentKey="vtgApp.province.filePath1">File Path 1</Translate>
              </span>
            </dt>
            <dd>{provinceEntity.filePath1}</dd>
            <dt>
              <span id="filePath2">
                <Translate contentKey="vtgApp.province.filePath2">File Path 2</Translate>
              </span>
            </dt>
            <dd>{provinceEntity.filePath2}</dd>
            <dt>
              <span id="filePath3">
                <Translate contentKey="vtgApp.province.filePath3">File Path 3</Translate>
              </span>
            </dt>
            <dd>{provinceEntity.filePath3}</dd>
            <dt>
              <span id="filePath4">
                <Translate contentKey="vtgApp.province.filePath4">File Path 4</Translate>
              </span>
            </dt>
            <dd>{provinceEntity.filePath4}</dd>
            <dt>
              <span id="filePath5">
                <Translate contentKey="vtgApp.province.filePath5">File Path 5</Translate>
              </span>
            </dt>
            <dd>{provinceEntity.filePath5}</dd>
            <dt>
              <span id="ratingId">
                <Translate contentKey="vtgApp.province.ratingId">Rating Id</Translate>
              </span>
            </dt>
            <dd>{provinceEntity.ratingId}</dd>
          </dl>
          <Button tag={Link} to="/entity/province-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/province-my-suffix/${provinceEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ province }: IRootState) => ({
  provinceEntity: province.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProvinceMySuffixDetail);
