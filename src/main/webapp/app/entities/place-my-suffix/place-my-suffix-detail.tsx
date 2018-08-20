import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './place-my-suffix.reducer';
import { IPlaceMySuffix } from 'app/shared/model/place-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPlaceMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class PlaceMySuffixDetail extends React.Component<IPlaceMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { placeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vtgApp.place.detail.title">Place</Translate> [<b>{placeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">
                <Translate contentKey="vtgApp.place.name">Name</Translate>
              </span>
            </dt>
            <dd>{placeEntity.name}</dd>
            <dt>
              <span id="slogan">
                <Translate contentKey="vtgApp.place.slogan">Slogan</Translate>
              </span>
            </dt>
            <dd>{placeEntity.slogan}</dd>
            <dt>
              <span id="address">
                <Translate contentKey="vtgApp.place.address">Address</Translate>
              </span>
            </dt>
            <dd>{placeEntity.address}</dd>
            <dt>
              <span id="rank">
                <Translate contentKey="vtgApp.place.rank">Rank</Translate>
              </span>
            </dt>
            <dd>{placeEntity.rank}</dd>
            <dt>
              <span id="commentId">
                <Translate contentKey="vtgApp.place.commentId">Comment Id</Translate>
              </span>
            </dt>
            <dd>{placeEntity.commentId}</dd>
            <dt>
              <span id="filePath1">
                <Translate contentKey="vtgApp.place.filePath1">File Path 1</Translate>
              </span>
            </dt>
            <dd>{placeEntity.filePath1}</dd>
            <dt>
              <span id="filePath2">
                <Translate contentKey="vtgApp.place.filePath2">File Path 2</Translate>
              </span>
            </dt>
            <dd>{placeEntity.filePath2}</dd>
            <dt>
              <span id="filePath3">
                <Translate contentKey="vtgApp.place.filePath3">File Path 3</Translate>
              </span>
            </dt>
            <dd>{placeEntity.filePath3}</dd>
            <dt>
              <span id="filePath4">
                <Translate contentKey="vtgApp.place.filePath4">File Path 4</Translate>
              </span>
            </dt>
            <dd>{placeEntity.filePath4}</dd>
            <dt>
              <span id="filePath5">
                <Translate contentKey="vtgApp.place.filePath5">File Path 5</Translate>
              </span>
            </dt>
            <dd>{placeEntity.filePath5}</dd>
            <dt>
              <span id="introduction">
                <Translate contentKey="vtgApp.place.introduction">Introduction</Translate>
              </span>
            </dt>
            <dd>{placeEntity.introduction}</dd>
            <dt>
              <span id="priceFrom">
                <Translate contentKey="vtgApp.place.priceFrom">Price From</Translate>
              </span>
            </dt>
            <dd>{placeEntity.priceFrom}</dd>
            <dt>
              <span id="priceTo">
                <Translate contentKey="vtgApp.place.priceTo">Price To</Translate>
              </span>
            </dt>
            <dd>{placeEntity.priceTo}</dd>
            <dt>
              <span id="latitude">
                <Translate contentKey="vtgApp.place.latitude">Latitude</Translate>
              </span>
            </dt>
            <dd>{placeEntity.latitude}</dd>
            <dt>
              <span id="longitude">
                <Translate contentKey="vtgApp.place.longitude">Longitude</Translate>
              </span>
            </dt>
            <dd>{placeEntity.longitude}</dd>
            <dt>
              <span id="provinceId">
                <Translate contentKey="vtgApp.place.provinceId">Province Id</Translate>
              </span>
            </dt>
            <dd>{placeEntity.provinceId}</dd>
          </dl>
          <Button tag={Link} to="/entity/place-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/place-my-suffix/${placeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ place }: IRootState) => ({
  placeEntity: place.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PlaceMySuffixDetail);
