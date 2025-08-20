require 'json'

colors = %w[
  white light_gray gray black brown red orange yellow lime green
  cyan light_blue blue purple magenta pink
]

colors.each do |color|
  # Normal item model points to block model
  item_json = {
    parent: "scalarutils:block/#{color}_flat_light"
  }
  File.write("#{color}_flat_light.json", JSON.pretty_generate(item_json))

  # Inverted item model points to inverted block model
  inverted_item_json = {
    parent: "scalarutils:block/inverted_#{color}_flat_light"
  }
  File.write("inverted_#{color}_flat_light.json", JSON.pretty_generate(inverted_item_json))
end
