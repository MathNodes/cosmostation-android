// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: akash/market/v1beta1/service.proto

package akash.market.v1beta1;

public final class Service {
  private Service() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\"akash/market/v1beta1/service.proto\022\024ak" +
      "ash.market.v1beta1\032\036akash/market/v1beta1" +
      "/bid.proto\032 akash/market/v1beta1/lease.p" +
      "roto2\350\003\n\003Msg\022[\n\tCreateBid\022\".akash.market" +
      ".v1beta1.MsgCreateBid\032*.akash.market.v1b" +
      "eta1.MsgCreateBidResponse\022X\n\010CloseBid\022!." +
      "akash.market.v1beta1.MsgCloseBid\032).akash" +
      ".market.v1beta1.MsgCloseBidResponse\022g\n\rW" +
      "ithdrawLease\022&.akash.market.v1beta1.MsgW" +
      "ithdrawLease\032..akash.market.v1beta1.MsgW" +
      "ithdrawLeaseResponse\022a\n\013CreateLease\022$.ak" +
      "ash.market.v1beta1.MsgCreateLease\032,.akas" +
      "h.market.v1beta1.MsgCreateLeaseResponse\022" +
      "^\n\nCloseLease\022#.akash.market.v1beta1.Msg" +
      "CloseLease\032+.akash.market.v1beta1.MsgClo" +
      "seLeaseResponseB(Z&github.com/ovrclk/aka" +
      "sh/x/market/typesb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          akash.market.v1beta1.BidOuterClass.getDescriptor(),
          akash.market.v1beta1.LeaseOuterClass.getDescriptor(),
        });
    akash.market.v1beta1.BidOuterClass.getDescriptor();
    akash.market.v1beta1.LeaseOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}